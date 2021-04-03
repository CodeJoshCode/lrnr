package com.jsimmons.lrnr.controllers;

import com.jsimmons.lrnr.registration.RegistrationRequest;
import com.jsimmons.lrnr.registration.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "api/v1/registration")
public class RegistrationController {

    Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    private final RegistrationService registrationService;

    @PostMapping
    public String register(RegistrationRequest request) {
        logger.info("calling registration post method");
        //this line returns the token that should be sent after clicking confirmation link from provided email
        registrationService.register(request);
        return "emailconfirmation";
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        logger.info("calling registration confirmation");
        if (!registrationService.confirmToken(token).equals("confirmed")) {
            throw new IllegalStateException("token was unable to be confirmed");
        }
        return "confirmed";
    }

}
