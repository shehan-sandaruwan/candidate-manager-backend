package lk.syscolabs.candidatemanager.controller;

import lk.syscolabs.candidatemanager.util.CustomResponse;
import lk.syscolabs.candidatemanager.util.CustomResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
    @GetMapping(path = "/")
    public ResponseEntity<CustomResponse> getRoot() {
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, "welcome to candidate manager API."), HttpStatus.OK);
    }


}
