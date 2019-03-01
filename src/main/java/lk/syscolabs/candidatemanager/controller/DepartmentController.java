package lk.syscolabs.candidatemanager.controller;

import lk.syscolabs.candidatemanager.model.Department;
import lk.syscolabs.candidatemanager.service.DepartmentService;
import lk.syscolabs.candidatemanager.util.CustomResponse;
import lk.syscolabs.candidatemanager.util.CustomResponseMessage;
import lk.syscolabs.candidatemanager.validator.DepartmentValidator;
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
@RequestMapping(path = "/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DepartmentValidator departmentValidator;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @GetMapping(path = "/")
    public ResponseEntity<CustomResponse> findAll() {
        List<Department> departments = departmentService.findAll();
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, departments), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> getOne(@PathVariable Integer id) {
        Department department = departmentService.getOne(id);
        if (department == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.DEPARTMENT_BY_DEPARTMENT_ID + " " + ValidatorFields.UNAVAILABLE),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, department), HttpStatus.OK);
    }

    @GetMapping(path = "/name/{name}")
    public ResponseEntity<CustomResponse> getOneByName(@PathVariable String name) {
        List<Department> departments = departmentService.findAllByName(name);
        if (departments.isEmpty()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.DEPARTMENT_BY_DEPARTMENT_NAME + " " + ValidatorFields.UNAVAILABLE),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, departments.get(0)), HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<CustomResponse> save(@RequestBody Department department) {
        ValidationResult validationResult = departmentValidator.validate(department);
        if (validationResult.hasError()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()), HttpStatus.BAD_REQUEST);
        }
        Department newDepartment = departmentService.save(department);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newDepartment), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> modify(@PathVariable Integer id, @RequestBody Department department) {
        Department oldDepartment = departmentService.getOne(id);
        if (oldDepartment == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.DEPARTMENT_BY_DEPARTMENT_ID + " " + ValidatorFields.UNAVAILABLE),
                    HttpStatus.BAD_REQUEST);
        }
        dozerBeanMapper.map(department, oldDepartment);
        oldDepartment.setId(id);
        ValidationResult validationResult = departmentValidator.validate(oldDepartment);
        if (validationResult.hasError()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()), HttpStatus.BAD_REQUEST);
        }
        Department newDepartment = departmentService.save(oldDepartment);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newDepartment), HttpStatus.OK);
    }
}