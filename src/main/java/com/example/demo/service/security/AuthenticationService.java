package com.example.demo.service.security;

import com.example.demo.model.dto.AuthenticationRequest;

public interface AuthenticationService {

    String generateToken(AuthenticationRequest authenticationRequest);
}
