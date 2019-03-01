package lk.syscolabs.candidatemanager.controller;

import lk.syscolabs.candidatemanager.model.Profile;
import lk.syscolabs.candidatemanager.service.ProfileService;
import lk.syscolabs.candidatemanager.util.CustomResponse;
import lk.syscolabs.candidatemanager.util.CustomResponseMessage;
import lk.syscolabs.candidatemanager.validator.ProfileValidator;
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
@RequestMapping(path = "/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private ProfileValidator profileValidator;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @GetMapping(path = "/")
    public ResponseEntity<CustomResponse> findAll() {
        List<Profile> profiles = profileService.findAll();
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, profiles), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> getOne(@PathVariable Integer id) {
        Profile profile = profileService.getOne(id);
        if (profile == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.PROFILE_BY_PROFILE_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, profile), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/name/{name}")
    public ResponseEntity<CustomResponse> getOneByName(@PathVariable String name) {
        List<Profile> profiles = profileService.findAllByName(name);
        if (profiles.isEmpty()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.PROFILE_BY_NAME + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, profiles.get(0)), HttpStatus.OK);
        }
    }

    @PostMapping(path = "/")
    public ResponseEntity<CustomResponse> save(@RequestBody Profile profile) {
        ValidationResult validationResult = profileValidator.validate(profile);
        if (validationResult.hasError()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()), HttpStatus.BAD_REQUEST);
        }
        Profile newProfile = profileService.save(profile);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newProfile), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> modify(@PathVariable Integer id, @RequestBody Profile profile) {
        Profile oldProfile = profileService.getOne(id);
        if (oldProfile == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.PROFILE_BY_PROFILE_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        dozerBeanMapper.map(profile, oldProfile);
        oldProfile.setId(id);
        ValidationResult validationResult = profileValidator.validate(oldProfile);
        if (validationResult.hasError()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()), HttpStatus.BAD_REQUEST);
        }
        Profile newProfile = profileService.save(oldProfile);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newProfile), HttpStatus.OK);
    }
}