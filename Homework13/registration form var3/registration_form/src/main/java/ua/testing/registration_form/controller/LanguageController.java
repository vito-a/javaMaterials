package ua.testing.registration_form.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.testing.registration_form.dto.LanguageDTO;
import ua.testing.registration_form.service.LanguageService;

@Slf4j
@RestController
@RequestMapping(value = "/api")
public class LanguageController {

    private final LanguageService languageService;

    @Autowired
    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/language", method = RequestMethod.POST)
    public void languageController(LanguageDTO language){
        log.info("{}", language);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
