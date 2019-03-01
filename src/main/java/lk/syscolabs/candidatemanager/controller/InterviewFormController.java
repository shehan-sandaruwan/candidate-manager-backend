package lk.syscolabs.candidatemanager.controller;

import lk.syscolabs.candidatemanager.model.InterviewForm;
import lk.syscolabs.candidatemanager.service.InterviewFormService;
import lk.syscolabs.candidatemanager.util.CustomResponse;
import lk.syscolabs.candidatemanager.util.CustomResponseMessage;
import lk.syscolabs.candidatemanager.validator.InterviewFormValidator;
import lk.syscolabs.candidatemanager.validator.ValidationResult;
import lk.syscolabs.candidatemanager.validator.ValidatorFields;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/interviewForm")
public class InterviewFormController {
    @Autowired
    private InterviewFormService interviewFormService;
    @Autowired
    private InterviewFormValidator interviewFormValidator;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @GetMapping(path = "/")
    public ResponseEntity<CustomResponse> findAll() {
        List<InterviewForm> interviewForms = interviewFormService.findAll();
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, interviewForms), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> getOne(@PathVariable Integer id) {
        InterviewForm interviewForm = interviewFormService.getOne(id);
        if (interviewForm == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.INTERVIEW_FORM_BY_INTERVIEW_FORM_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, interviewForm), HttpStatus.OK);
        }
    }

    @PostMapping(path = "/")
    public ResponseEntity<CustomResponse> save(@RequestBody InterviewForm interviewForm) {
        ValidationResult validationResult = interviewFormValidator.validate(interviewForm);
        if (validationResult.hasError()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()), HttpStatus.BAD_REQUEST);
        }
        InterviewForm newInterviewForm = interviewFormService.save(interviewForm);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newInterviewForm), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> modify(@PathVariable Integer id, @RequestBody InterviewForm interviewForm) {
        InterviewForm oldInterviewForm = interviewFormService.getOne(id);
        if (oldInterviewForm == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.INTERVIEW_FORM_BY_INTERVIEW_FORM_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        dozerBeanMapper.map(interviewForm, oldInterviewForm);
        oldInterviewForm.setId(id);
        ValidationResult validationResult = interviewFormValidator.validate(oldInterviewForm);
        if (validationResult.hasError()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()), HttpStatus.BAD_REQUEST);
        }
        InterviewForm newInterviewForm = interviewFormService.save(oldInterviewForm);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newInterviewForm), HttpStatus.OK);
    }

}