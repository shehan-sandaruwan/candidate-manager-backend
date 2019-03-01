package lk.syscolabs.candidatemanager.controller;

import lk.syscolabs.candidatemanager.model.Position;
import lk.syscolabs.candidatemanager.service.PositionService;
import lk.syscolabs.candidatemanager.util.CustomResponse;
import lk.syscolabs.candidatemanager.util.CustomResponseMessage;
import lk.syscolabs.candidatemanager.validator.PositionValidator;
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
@RequestMapping(path = "/position")
public class PositionController {
    @Autowired
    private PositionService positionService;
    @Autowired
    private PositionValidator positionValidator;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @GetMapping(path = "/")
    public ResponseEntity<CustomResponse> findAll() {
        List<Position> positions = positionService.findAll();
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, positions), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> getOne(@PathVariable Integer id) {
        Position position = positionService.getOne(id);
        if (position == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.POSITION_BY_POSITION_ID + " " + ValidatorFields.UNAVAILABLE),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, position), HttpStatus.OK);
    }

    @GetMapping(path = "/name/{name}")
    public ResponseEntity<CustomResponse> getOneByName(@PathVariable String name) {
        List<Position> positions = positionService.findAllByName(name);
        if (positions.isEmpty()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.POSITION_BY_NAME + " " + ValidatorFields.UNAVAILABLE),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, positions.get(0)), HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<CustomResponse> save(@RequestBody Position position) {
        ValidationResult validationResult = positionValidator.validate(position);
        if (validationResult.hasError()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()),
                    HttpStatus.BAD_REQUEST);
        }
        Position newPosition = positionService.save(position);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newPosition), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> modify(@PathVariable Integer id, @RequestBody Position position) {
        Position oldPosition = positionService.getOne(id);
        if (oldPosition == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.POSITION_BY_POSITION_ID + " " + ValidatorFields.UNAVAILABLE),
                    HttpStatus.BAD_REQUEST);
        }
        dozerBeanMapper.map(position, oldPosition);
        oldPosition.setId(id);
        ValidationResult validationResult = positionValidator.validate(oldPosition);
        if (validationResult.hasError()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()),
                    HttpStatus.BAD_REQUEST);
        }
        Position newPosition = positionService.save(oldPosition);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newPosition), HttpStatus.OK);
    }
}