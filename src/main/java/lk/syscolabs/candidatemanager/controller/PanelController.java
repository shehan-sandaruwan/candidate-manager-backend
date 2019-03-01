package lk.syscolabs.candidatemanager.controller;

import lk.syscolabs.candidatemanager.model.Panel;
import lk.syscolabs.candidatemanager.service.PanelService;
import lk.syscolabs.candidatemanager.util.CustomResponse;
import lk.syscolabs.candidatemanager.util.CustomResponseMessage;
import lk.syscolabs.candidatemanager.validator.PanelValidator;
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
@RequestMapping(path = "/panel")
public class PanelController {
    @Autowired
    private PanelService panelService;
    @Autowired
    private PanelValidator panelValidator;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @GetMapping(path = "/")
    public ResponseEntity<CustomResponse> findAll() {
        List<Panel> panels = panelService.findAll();
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, panels), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> getOne(@PathVariable Integer id) {
        Panel panel = panelService.getOne(id);
        if (panel == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.PANEL_BY_PANEL_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, panel), HttpStatus.OK);
        }
    }

    @PostMapping(path = "/")
    public ResponseEntity<CustomResponse> save(@RequestBody Panel panel) {
        ValidationResult validationResult = panelValidator.validate(panel);
        if (validationResult.hasError()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()), HttpStatus.BAD_REQUEST);
        }
        Panel newPanel = panelService.save(panel);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newPanel), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> save(@PathVariable Integer id, @RequestBody Panel panel) {
        Panel oldPanel = panelService.getOne(id);
        if (oldPanel == null) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, ValidatorFields.PANEL_BY_PANEL_ID + " " + ValidatorFields.UNAVAILABLE), HttpStatus.BAD_REQUEST);
        }
        dozerBeanMapper.map(panel, oldPanel);
        oldPanel.setId(id);
        ValidationResult validationResult = panelValidator.validate(oldPanel);
        if (validationResult.hasError()) {
            return new ResponseEntity<>(new CustomResponse(400, CustomResponseMessage.ERROR, validationResult.getErrors()), HttpStatus.BAD_REQUEST);
        }
        Panel newPanel = panelService.save(oldPanel);
        return new ResponseEntity<>(new CustomResponse(200, CustomResponseMessage.SUCCESS, newPanel), HttpStatus.OK);
    }
}