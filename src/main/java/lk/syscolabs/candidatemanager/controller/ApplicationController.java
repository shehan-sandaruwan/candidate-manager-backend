package lk.syscolabs.candidatemanager.controller;

import lk.syscolabs.candidatemanager.model.Application;
import lk.syscolabs.candidatemanager.model.CommonApplication;
import lk.syscolabs.candidatemanager.model.User;
import lk.syscolabs.candidatemanager.service.ApplicationService;
import lk.syscolabs.candidatemanager.service.DepartmentStateService;
import lk.syscolabs.candidatemanager.service.UserService;
import lk.syscolabs.candidatemanager.util.CustomResponse;
import lk.syscolabs.candidatemanager.util.CustomResponseMessage;
import lk.syscolabs.candidatemanager.validator.ApplicationValidator;
import lk.syscolabs.candidatemanager.validator.ValidationResult;
import lk.syscolabs.candidatemanager.validator.ValidatorFields;
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
@RequestMapping(path = "/application")
public class ApplicationController {
    private static final Set<String> stateNames = new HashSet<>(Arrays.asList(
            "pre-check-rejected", "submitted", "on-hold", "withdrawn", "no-show", "pre-checked", "hr-short-listed", "hr-rejected", "line-short-listed", "line-rejected", "phone-rejected", "offer-accepted", "offer-rejected", "interviewed", "interview-rejected", "blacklisted", "interview-scheduled","selected"));
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private ApplicationValidator applicationValidator;
    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentStateService departmentStateService;

    @GetMapping(path = "/")
    public ResponseEntity<CustomResponse> findAll() {
        List<Application> applications = applicationService.findAll();
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, applications), HttpStatus.OK);
    }

    @GetMapping(path = "/match")
    public ResponseEntity<CustomResponse> match(@RequestParam(value = "firstName") String firstName,
                                                @RequestParam(value = "lastName") String lastName,
                                                @RequestParam(value = "email") String email,
                                                @RequestParam(value = "phone") String phone,
                                                @RequestParam(value = "nic") String nic, @RequestParam(value = "candidateId") Integer candidateId) {
        List<CommonApplication> applications = applicationService.match(firstName, lastName, email, phone, nic, candidateId);
        return new ResponseEntity<>(new CustomResponse(200, "success", applications), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> getOne(@PathVariable Integer id) {
        Application application = applicationService.getOne(id);
        if (application == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.APPLICATION_BY_APPLICATION_ID + " " + ValidatorFields.UNAVAILABLE),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, application), HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<CustomResponse> save(@RequestBody Application application) {
        ValidationResult validationResult = applicationValidator.validate(application);
        if (validationResult.hasError()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()),
                    HttpStatus.BAD_REQUEST);
        }
        Application newApplication = applicationService.save(application);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newApplication), HttpStatus.OK);
    }

    @GetMapping(path = "/state/{stateName}")
    public ResponseEntity<CustomResponse> findAllByStateName(@PathVariable String stateName) {
        if (!stateNames.contains(stateName)) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.STATE_BY_STATE_NAME + " " + ValidatorFields.UNAVAILABLE),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, applicationService.findAllByStateName(stateName)), HttpStatus.OK);
    }

    @GetMapping(path = "/assignedTo/{id}")
    public ResponseEntity<CustomResponse> findAllByAssignedTo(@PathVariable Integer id) {
        User assignedTo = userService.getOne(id);
        if (assignedTo == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.USER_BY_USER_ID + " " + ValidatorFields.UNAVAILABLE),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, departmentStateService.findAllByAssignedTo(assignedTo)), HttpStatus.OK);
    }
}