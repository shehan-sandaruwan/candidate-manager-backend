package lk.syscolabs.candidatemanager.controller;

import lk.syscolabs.candidatemanager.model.AdminPrivilege;
import lk.syscolabs.candidatemanager.model.User;
import lk.syscolabs.candidatemanager.service.AdminPrivilegeService;
import lk.syscolabs.candidatemanager.service.UserService;
import lk.syscolabs.candidatemanager.util.CustomResponse;
import lk.syscolabs.candidatemanager.util.CustomResponseMessage;
import lk.syscolabs.candidatemanager.validator.AdminPrivilegeValidator;
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
@RequestMapping("/adminPrivilege")
public class AdminPrivilegeController {

    @Autowired
    private AdminPrivilegeService adminPrivilegeService;
    @Autowired
    private UserService userService;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;
    @Autowired
    private AdminPrivilegeValidator adminPrivilegeValidator;

    @GetMapping(path = "/")
    public ResponseEntity<CustomResponse> findAll() {
        List<AdminPrivilege> adminPriviledgese = adminPrivilegeService.findAll();
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, adminPriviledgese), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> getOne(@PathVariable Integer id) {
        AdminPrivilege adminPrivilege = adminPrivilegeService.getOne(id);
        if (adminPrivilege == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.PRIVILEGE_BY_PRIVILEGE_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, adminPrivilege), HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<CustomResponse> save(@RequestBody AdminPrivilege adminPrivilege) {
        ValidationResult validationResult = adminPrivilegeValidator.validate(adminPrivilege);
        if (validationResult.hasError()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()), HttpStatus.BAD_REQUEST);
        }
        AdminPrivilege newAdminPrivilege = adminPrivilegeService.save(adminPrivilege);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newAdminPrivilege), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> modify(@PathVariable Integer id, @RequestBody AdminPrivilege adminPrivilege) {
        AdminPrivilege oldAdminPrivilege = adminPrivilegeService.getOne(id);
        if (oldAdminPrivilege == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.PRIVILEGE_BY_PRIVILEGE_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        dozerBeanMapper.map(adminPrivilege, oldAdminPrivilege);
        oldAdminPrivilege.setId(id);
        ValidationResult validationResult = adminPrivilegeValidator.validate(oldAdminPrivilege);
        if (validationResult.hasError()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()), HttpStatus.BAD_REQUEST);
        }
        AdminPrivilege newAdminPrivilege = adminPrivilegeService.save(oldAdminPrivilege);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newAdminPrivilege), HttpStatus.OK);
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<CustomResponse> getOneByUserId(@PathVariable Integer id) {
        User user = userService.getOne(id);
        if (user == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.USER_BY_USER_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, adminPrivilegeService.findAllByUserByUserId(user)), HttpStatus.OK);
    }

}
