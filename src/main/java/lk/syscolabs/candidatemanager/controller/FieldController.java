package lk.syscolabs.candidatemanager.controller;

import lk.syscolabs.candidatemanager.model.Field;
import lk.syscolabs.candidatemanager.service.FieldService;
import lk.syscolabs.candidatemanager.util.CustomResponse;
import lk.syscolabs.candidatemanager.util.CustomResponseMessage;
import lk.syscolabs.candidatemanager.validator.FieldValidator;
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
@RequestMapping(path = "/field")
public class FieldController {
    @Autowired
    private FieldService fieldService;
    @Autowired
    private FieldValidator fieldValidator;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @GetMapping(path = "/")
    public ResponseEntity<CustomResponse> findAll() {
        List<Field> fields = fieldService.findAll();
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, fields), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> getOne(@PathVariable Integer id) {
        Field field = fieldService.getOne(id);
        if (field == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.FIELD_BY_FIELD_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, field), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/name/{name}")
    public ResponseEntity<CustomResponse> getOneByName(@PathVariable String name) {
        List<Field> fields = fieldService.findAllByName(name);
        if (fields.isEmpty()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.FIELD_BY_FIELD_NAME + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, fields.get(0)), HttpStatus.OK);
        }
    }

    @PostMapping(path = "/")
    public ResponseEntity<CustomResponse> save(@RequestBody Field field) {
        ValidationResult validationResult = fieldValidator.validate(field);
        if (validationResult.hasError()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()), HttpStatus.BAD_REQUEST);
        }
        Field newField = fieldService.save(field);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newField), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> modify(@PathVariable Integer id, @RequestBody Field field) {
        Field oldField = fieldService.getOne(id);
        if (oldField == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.FIELD_BY_FIELD_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        dozerBeanMapper.map(field, oldField);
        oldField.setId(id);
        ValidationResult validationResult = fieldValidator.validate(oldField);
        if (validationResult.hasError()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()), HttpStatus.BAD_REQUEST);
        }
        Field newField = fieldService.save(oldField);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newField), HttpStatus.OK);
    }
}