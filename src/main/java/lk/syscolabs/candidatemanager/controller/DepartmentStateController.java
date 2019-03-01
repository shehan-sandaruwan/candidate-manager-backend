package lk.syscolabs.candidatemanager.controller;

import lk.syscolabs.candidatemanager.model.DepartmentState;
import lk.syscolabs.candidatemanager.service.DepartmentStateService;
import lk.syscolabs.candidatemanager.util.CustomResponse;
import lk.syscolabs.candidatemanager.util.CustomResponseMessage;
import lk.syscolabs.candidatemanager.validator.DepartmentStateValidator;
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
@RequestMapping(path = "/departmentState")
public class DepartmentStateController {
    @Autowired
    private DepartmentStateService departmentStateService;
    @Autowired
    private DepartmentStateValidator departmentStateValidator;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @GetMapping(path = "/")
    public ResponseEntity<CustomResponse> findAll() {
        List<DepartmentState> departmentStates = departmentStateService.findAll();
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, departmentStates), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> getOne(@PathVariable Integer id) {
        DepartmentState departmentState = departmentStateService.getOne(id);
        if (departmentState == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.DEPARTMENT_STATE_BY_DEPARTMENT_STATE_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, departmentState), HttpStatus.OK);
        }
    }

    @PostMapping(path = "/")
    public ResponseEntity<CustomResponse> save(@RequestBody DepartmentState departmentState) {
        ValidationResult validationResult = departmentStateValidator.validate(departmentState);
        if (validationResult.hasError()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()), HttpStatus.BAD_REQUEST);
        }
        DepartmentState newDepartmentState = departmentStateService.save(departmentState);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newDepartmentState), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> modify(@PathVariable Integer id, @RequestBody DepartmentState departmentState) {
        DepartmentState oldDepartmentState = departmentStateService.getOne(id);
        if (oldDepartmentState == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, null), HttpStatus.BAD_REQUEST);
        }
        dozerBeanMapper.map(departmentState, oldDepartmentState);
        oldDepartmentState.setId(id);
        ValidationResult validationResult = departmentStateValidator.validate(oldDepartmentState);
        if (validationResult.hasError()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()), HttpStatus.BAD_REQUEST);
        }
        DepartmentState newDepartmentState = departmentStateService.save(departmentState);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newDepartmentState), HttpStatus.OK);
    }
}