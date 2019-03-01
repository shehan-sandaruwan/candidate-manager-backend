package lk.syscolabs.candidatemanager.controller;

import lk.syscolabs.candidatemanager.model.Position;
import lk.syscolabs.candidatemanager.model.User;
import lk.syscolabs.candidatemanager.service.PositionService;
import lk.syscolabs.candidatemanager.service.UserService;
import lk.syscolabs.candidatemanager.util.AuthMessage;
import lk.syscolabs.candidatemanager.util.CustomResponse;
import lk.syscolabs.candidatemanager.util.CustomResponseMessage;
import lk.syscolabs.candidatemanager.validator.UserValidator;
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
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;
    @Autowired
    private PositionService positionService;

    @GetMapping(path = "/")
    public ResponseEntity<CustomResponse> findAll() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, users), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> getOne(@PathVariable Integer id) {
        User user = userService.getOne(id);
        if (user == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.USER_BY_USER_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, user), HttpStatus.OK);
    }

    @GetMapping(path = "/email/{email}")
    public ResponseEntity<CustomResponse> getOne(@PathVariable String email) {
        if (email != null) email = email.toLowerCase();
        List<User> users = userService.findAllByEmail(email);
        if (users.isEmpty()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.USER_BY_EMAIL + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, users.get(0)), HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<CustomResponse> save(@RequestBody User user) {
        ValidationResult validationResult = userValidator.validate(user);
        if (validationResult.hasError()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()), HttpStatus.BAD_REQUEST);
        }
        user.setEmail(user.getEmail().toLowerCase());
        User newUser = userService.save(user);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newUser), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> modify(@PathVariable Integer id, @RequestBody User user) {
        User oldUser = userService.getOne(id);
        if (oldUser == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.USER_BY_USER_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        dozerBeanMapper.map(user, oldUser);
        oldUser.setId(id);
        ValidationResult validationResult = userValidator.validate(oldUser);
        if (validationResult.hasError()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()), HttpStatus.BAD_REQUEST);
        }
        User newUser = userService.save(oldUser);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newUser), HttpStatus.OK);
    }

    @GetMapping(path = "/privilege/{positionId}/{action}")
    public ResponseEntity<CustomResponse> getAllByPrivileges(@PathVariable String action, @PathVariable Integer positionId) {
        if (!action.equals("interview") && !action.equals("short-list")) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.PRIVILEGE_BY_ACTION + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        Position position = positionService.getOne(positionId);
        if (position == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.POSITION_BY_POSITION_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        byte canInterview = 0;
        byte canShortList = 0;
        if (action.equals("interview")) canInterview = 1;
        if (action.equals("short-list")) canShortList = 1;
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, userService.findAllByPositionByPositionIdAndCanShortListOrCanInterview(position, canShortList, canInterview)), HttpStatus.OK);
    }

    @PostMapping(path = "/auth/")
    public ResponseEntity<CustomResponse> authenticate(@RequestBody AuthMessage authMessage) {
        userService.authenticate(authMessage);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, CustomResponseMessage.EMAIL_SENT), HttpStatus.OK);
    }
}