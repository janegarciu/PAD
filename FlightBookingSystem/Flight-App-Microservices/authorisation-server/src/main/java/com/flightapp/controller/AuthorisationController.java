package com.flightapp.controller;

import com.flightapp.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AuthorisationController {

    private final AuthenticationService authenticationService;

    public AuthorisationController(final AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return authenticationService.login(username,password);
    }

    @PostMapping("/signUp")
    public String signUp(@RequestParam String username, @RequestParam String password) {
        return authenticationService.signUp(username,password);
    }

    @PostMapping("/confirmUser")
    public String confirmUser(@RequestParam String username, @RequestParam String code) {
        return authenticationService.confirmSignUp(username,code);
    }
}
