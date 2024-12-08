package hu.unideb.inf.sfm.bau_backend.controller;

import hu.unideb.inf.sfm.bau_backend.data.FormDto;
import hu.unideb.inf.sfm.bau_backend.entity.FormEntity;
import hu.unideb.inf.sfm.bau_backend.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController //restAPI miatt kell Rest és nem sima
@CrossOrigin(origins = "http://localhost:3000")
public class FormController {

    @Autowired
    private FormService formService;
    @PostMapping("/form")
    public FormEntity addForm(@RequestBody FormDto formDto) {
        return formService.addForm(formDto);
    }

}
