package lk.syscolabs.candidatemanager.controller;

import lk.syscolabs.candidatemanager.model.ProfileField;
import lk.syscolabs.candidatemanager.service.ProfileFieldService;
import lk.syscolabs.candidatemanager.util.CustomResponse;
import lk.syscolabs.candidatemanager.util.CustomResponseMessage;
import lk.syscolabs.candidatemanager.validator.ProfileFieldValidator;
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
@RequestMapping(path = "/profileField")
public class ProfileFieldController {
    @Autowired
    private ProfileFieldService profileFieldService;
    @Autowired
    private ProfileFieldValidator profileFieldValidator;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @GetMapping(path = "/")
    public ResponseEntity<CustomResponse> findAll() {
        List<ProfileField> profileFields = profileFieldService.findAll();
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, profileFields), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> getOne(@PathVariable Integer id) {
        ProfileField profileField = profileFieldService.getOne(id);
        if (profileField == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.PROFILE_FIELD_BY_PROFILE_FIELD_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, profileField), HttpStatus.OK);
        }
    }

    @PostMapping(path = "/")
    public ResponseEntity<CustomResponse> save(@RequestBody ProfileField profileField) {
        ValidationResult validationResult = profileFieldValidator.validate(profileField);
        if (validationResult.hasError()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()), HttpStatus.BAD_REQUEST);
        }
        ProfileField newProfileField = profileFieldService.save(profileField);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newProfileField), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> modify(@PathVariable Integer id, @RequestBody ProfileField profileField) {
        ProfileField oldProfileField = profileFieldService.getOne(id);
        if (oldProfileField == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.PROFILE_FIELD_BY_PROFILE_FIELD_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        dozerBeanMapper.map(profileField, oldProfileField);
        oldProfileField.setId(id);
        ValidationResult validationResult = profileFieldValidator.validate(oldProfileField);
        if (validationResult.hasError()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()), HttpStatus.BAD_REQUEST);
        }
        ProfileField newProfileField = profileFieldService.save(oldProfileField);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newProfileField), HttpStatus.OK);
    }
}