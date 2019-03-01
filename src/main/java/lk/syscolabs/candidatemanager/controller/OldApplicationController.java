package lk.syscolabs.candidatemanager.controller;

import lk.syscolabs.candidatemanager.model.OldApplication;
import lk.syscolabs.candidatemanager.service.OldApplicationService;
import lk.syscolabs.candidatemanager.util.CustomResponse;
import lk.syscolabs.candidatemanager.util.CustomResponseMessage;
import lk.syscolabs.candidatemanager.validator.ValidationResult;
import lk.syscolabs.candidatemanager.validator.ValidatorFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/oldApplication")
public class OldApplicationController {
    @Autowired
    private OldApplicationService oldApplicationService;

    @GetMapping(path = "/")
    public ResponseEntity<CustomResponse> findAll() {
        List<OldApplication> oldApplications = oldApplicationService.findAll();
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, oldApplications), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> getOne(@PathVariable Integer id) {
        OldApplication oldApplication = oldApplicationService.getOne(id);
        if (oldApplication == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.APPLICATION_BY_APPLICATION_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, oldApplication), HttpStatus.OK);
        }
    }

    @PostMapping(path = "/")
    public ResponseEntity<CustomResponse> save(@RequestBody OldApplication oldApplication) {
        ValidationResult validationResult = new ValidationResult();
        if (validationResult.hasError()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()), HttpStatus.BAD_REQUEST);
        }
        OldApplication newOldApplication = oldApplicationService.save(oldApplication);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newOldApplication), HttpStatus.OK);
    }
}