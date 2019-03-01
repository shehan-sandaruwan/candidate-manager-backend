package lk.syscolabs.candidatemanager.controller;

import lk.syscolabs.candidatemanager.model.Application;
import lk.syscolabs.candidatemanager.model.Schedule;
import lk.syscolabs.candidatemanager.model.User;
import lk.syscolabs.candidatemanager.service.ApplicationService;
import lk.syscolabs.candidatemanager.service.ScheduleService;
import lk.syscolabs.candidatemanager.service.UserService;
import lk.syscolabs.candidatemanager.util.CustomResponse;
import lk.syscolabs.candidatemanager.util.CustomResponseMessage;
import lk.syscolabs.candidatemanager.validator.ScheduleValidator;
import lk.syscolabs.candidatemanager.validator.ValidationResult;
import lk.syscolabs.candidatemanager.validator.ValidatorFields;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(path = "/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ScheduleValidator scheduleValidator;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationService applicationService;

    @GetMapping(path = "/")
    public ResponseEntity<CustomResponse> findAll() {
        List<Schedule> schedules = scheduleService.findAll();
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, schedules), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> getOne(@PathVariable Integer id) {
        Schedule schedule = scheduleService.getOne(id);
        if (schedule == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.SCHEDULE_BY_SCHEDULE_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, schedule), HttpStatus.OK);
    }

    @GetMapping(path = "/assignedTo/{id}")
    public ResponseEntity<CustomResponse> getAllByAssignedTo(@PathVariable Integer id) {
        User user = userService.getOne(id);
        if (user == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.USER_BY_USER_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, scheduleService.findAllByUserId(id)), HttpStatus.OK);
    }

    @PostMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> save(@RequestBody Schedule schedule, @PathVariable Integer id) {
        User user = userService.getOne(id);
        if (user == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.USER_BY_USER_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        ValidationResult validationResult = scheduleValidator.validate(schedule);
        if (validationResult.hasError()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()), HttpStatus.BAD_REQUEST);
        }
        Schedule newSchedule = scheduleService.save(schedule, user);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newSchedule), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> modify(@PathVariable Integer id, @RequestBody Schedule schedule) {
        Schedule oldSchedule = scheduleService.getOne(id);
        if (oldSchedule == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.SCHEDULE_BY_SCHEDULE_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        dozerBeanMapper.map(schedule, oldSchedule);
        oldSchedule.setId(id);
        ValidationResult validationResult = scheduleValidator.validate(oldSchedule);
        if (validationResult.hasError()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()), HttpStatus.BAD_REQUEST);
        }
        Schedule newSchedule = scheduleService.save(oldSchedule, new User());
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newSchedule), HttpStatus.OK);
    }

    @PostMapping(path = "/{scheduleId}/{stateName}/{userId}")
    public ResponseEntity<CustomResponse> interview(@RequestBody Schedule newSchedule, @PathVariable Integer scheduleId, @PathVariable Integer userId, @PathVariable String stateName) {
        User user = userService.getOne(userId);
        if (user == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.USER_BY_USER_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        Set<String> stateNames = new HashSet<>(Arrays.asList("on-hold", "no-show", "interviewed", "interview-rejected", "blacklisted"));
        if (!stateNames.contains(stateName)) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.STATE_BY_STATE_NAME + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        Schedule schedule = scheduleService.getOne(scheduleId);
        if (schedule == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.SCHEDULE_BY_SCHEDULE_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        schedule.setFinalRating(newSchedule.getFinalRating());
        scheduleService.performInterview(schedule, stateName, user);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newSchedule), HttpStatus.OK);
    }

    @GetMapping(path = "/application/{id}")
    public ResponseEntity<CustomResponse> findPendingByApplicationId(@PathVariable Integer id) {
        Application application = applicationService.getOne(id);
        if (application == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.APPLICATION_BY_APPLICATION_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        List<Schedule> schedules = scheduleService.findAllPendingByApplicationByApplicationId(application);
        if (schedules.isEmpty()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, "no schedules"), HttpStatus.BAD_REQUEST);
        }
        Schedule newSchedule = schedules.get(schedules.size()-1);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newSchedule), HttpStatus.OK);
    }

    @GetMapping(path = "/application/all/{id}")
    public ResponseEntity<CustomResponse> findAllInterviewedByApplicationId(@PathVariable Integer id) {
        Application application = applicationService.getOne(id);
        if (application == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.APPLICATION_BY_APPLICATION_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        List<Schedule> schedules = scheduleService.findAllInterviewedByApplicationId(application);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, schedules), HttpStatus.OK);
    }
}