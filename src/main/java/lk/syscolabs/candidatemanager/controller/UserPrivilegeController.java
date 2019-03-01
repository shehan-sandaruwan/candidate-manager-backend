package lk.syscolabs.candidatemanager.controller;

import lk.syscolabs.candidatemanager.model.User;
import lk.syscolabs.candidatemanager.model.UserPrivilege;
import lk.syscolabs.candidatemanager.service.UserPrivilegeService;
import lk.syscolabs.candidatemanager.service.UserService;
import lk.syscolabs.candidatemanager.util.CustomResponse;
import lk.syscolabs.candidatemanager.util.CustomResponseMessage;
import lk.syscolabs.candidatemanager.validator.UserPrivilegeValidator;
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
@RequestMapping(path = "/userPrivilege")
public class UserPrivilegeController {
    @Autowired
    private UserPrivilegeService userPrivilegeService;
    @Autowired
    private UserPrivilegeValidator userPrivilegeValidator;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;
    @Autowired
    private UserService userService;


    @GetMapping(path = "/")
    public ResponseEntity<CustomResponse> findAll() {
        List<UserPrivilege> userPriviledgese = userPrivilegeService.findAll();
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, userPriviledgese), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> getOne(@PathVariable Integer id) {
        UserPrivilege userPrivilege = userPrivilegeService.getOne(id);
        if (userPrivilege == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.PRIVILEGE_BY_PRIVILEGE_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, userPrivilege), HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<CustomResponse> save(@RequestBody UserPrivilege userPrivilege) {
        ValidationResult validationResult = userPrivilegeValidator.validate(userPrivilege);
        if (validationResult.hasError()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()), HttpStatus.BAD_REQUEST);
        }
        UserPrivilege newUserPrivilege = userPrivilegeService.save(userPrivilege);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newUserPrivilege), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> modify(@PathVariable Integer id, @RequestBody UserPrivilege userPrivilege) {
        UserPrivilege oldUserPrivilege = userPrivilegeService.getOne(id);
        if (oldUserPrivilege == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.USER_BY_USER_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        dozerBeanMapper.map(userPrivilege, oldUserPrivilege);
        oldUserPrivilege.setId(id);
        ValidationResult validationResult = userPrivilegeValidator.validate(oldUserPrivilege);
        if (validationResult.hasError()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()), HttpStatus.BAD_REQUEST);
        }
        UserPrivilege newUserPrivilege = userPrivilegeService.save(oldUserPrivilege);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newUserPrivilege), HttpStatus.OK);
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<CustomResponse> getOneByUserId(@PathVariable Integer id) {
        User user = userService.getOne(id);
        if (user == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.USER_BY_USER_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, userPrivilegeService.findAllByUserByUserId(user)), HttpStatus.OK);
    }
}