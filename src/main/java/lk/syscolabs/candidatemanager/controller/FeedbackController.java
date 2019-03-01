package lk.syscolabs.candidatemanager.controller;

import lk.syscolabs.candidatemanager.model.Feedback;
import lk.syscolabs.candidatemanager.model.Schedule;
import lk.syscolabs.candidatemanager.service.FeedbackService;
import lk.syscolabs.candidatemanager.service.ScheduleService;
import lk.syscolabs.candidatemanager.util.CustomResponse;
import lk.syscolabs.candidatemanager.util.CustomResponseMessage;
import lk.syscolabs.candidatemanager.validator.FeedbackValidator;
import lk.syscolabs.candidatemanager.validator.ValidationResult;
import lk.syscolabs.candidatemanager.validator.ValidatorFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private FeedbackValidator feedbackValidator;
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping(path = "/")
    public ResponseEntity<CustomResponse> findAll() {
        List<Feedback> feedbacks = feedbackService.findAll();
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, feedbacks), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> getOne(@PathVariable Integer id) {
        Feedback feedback = feedbackService.getOne(id);
        if (feedback == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.FEEDBACK_BY_FEEDBACK_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, feedback), HttpStatus.OK);
        }

    }

    @GetMapping(path = "/schedule/{id}")
    public ResponseEntity<CustomResponse> getAllBySchedule(@PathVariable Integer id) {
        Schedule schedule = scheduleService.getOne(id);
        if (schedule == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.FEEDBACK_BY_FEEDBACK_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, feedbackService.findAllByScheduleByScheduleId(schedule)), HttpStatus.OK);
        }

    }

//    @PostMapping(path = "/")
//    public ResponseEntity<CustomResponse> save(@RequestBody Feedback feedback) {
//        ValidationResult validationResult = feedbackValidator.validate(feedback);
//        if (validationResult.hasError()) {
//            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()), HttpStatus.BAD_REQUEST);
//        }
//        Feedback newFeedback = feedbackService.save(feedback);
//        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newFeedback), HttpStatus.OK);
//    }

    @PostMapping(path = "/")
    public ResponseEntity<CustomResponse> save(@RequestBody List<Feedback> feedbacks) {
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, feedbackService.save(feedbacks)), HttpStatus.OK);
    }
}