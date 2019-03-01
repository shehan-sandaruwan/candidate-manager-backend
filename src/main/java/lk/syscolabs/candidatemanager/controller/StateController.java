package lk.syscolabs.candidatemanager.controller;

import lk.syscolabs.candidatemanager.model.Application;
import lk.syscolabs.candidatemanager.model.State;
import lk.syscolabs.candidatemanager.service.ApplicationService;
import lk.syscolabs.candidatemanager.service.StateService;
import lk.syscolabs.candidatemanager.util.CustomResponse;
import lk.syscolabs.candidatemanager.util.CustomResponseMessage;
import lk.syscolabs.candidatemanager.validator.StateValidator;
import lk.syscolabs.candidatemanager.validator.ValidationResult;
import lk.syscolabs.candidatemanager.validator.ValidatorFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/state")
public class StateController {
    @Autowired
    private StateService stateService;
    @Autowired
    private StateValidator stateValidator;
    @Autowired
    private ApplicationService applicationService;

    @GetMapping(path = "/")
    public ResponseEntity<CustomResponse> findAll() {
        List<State> states = stateService.findAll();
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, states), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> getOne(@PathVariable Integer id) {
        State state = stateService.getOne(id);
        if (state == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.STATE_BY_STATE_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, state), HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<CustomResponse> save(@RequestBody State state) {
        ValidationResult validationResult = stateValidator.validate(state);
        if (validationResult.hasError()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()), HttpStatus.BAD_REQUEST);
        }
        State newState = stateService.save(state);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newState), HttpStatus.OK);
    }

    @GetMapping(path = "/application/{id}")
    public ResponseEntity<CustomResponse> getAllStatesOfApplication(@PathVariable Integer id) {
        Application application = applicationService.getOne(id);
        if (application == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.APPLICATION_BY_APPLICATION_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, stateService.findAllByApplicationByApplicationId(application)), HttpStatus.OK);
    }

    @GetMapping(path = "/currentstateofapplication/{id}")
    public ResponseEntity<CustomResponse> getCurrentStateOfApplication(@PathVariable Integer id) {
        Application application = applicationService.getOne(id);
        if (application == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.APPLICATION_BY_APPLICATION_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, stateService.getActiveState(application)), HttpStatus.OK);
    }
}