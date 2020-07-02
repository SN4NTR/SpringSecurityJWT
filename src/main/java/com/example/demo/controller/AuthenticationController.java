package com.example.demo.controller;

import com.example.demo.model.dto.AuthenticationRequest;
import com.example.demo.service.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public String generate(@RequestBody AuthenticationRequest authenticationRequest) {
        return authenticationService.generateToken(authenticationRequest);
    }
}
