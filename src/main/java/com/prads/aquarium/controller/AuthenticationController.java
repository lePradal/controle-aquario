package com.prads.aquarium.controller;

import com.prads.aquarium.config.security.TokenService;
import com.prads.aquarium.controller.dto.TokenDTO;
import com.prads.aquarium.controller.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authenticate(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken loginData = form.convert();
        try {
            Authentication authentication = authManager.authenticate(loginData);
            String token = tokenService.generateToken(authentication);

            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
        } catch (AuthenticationException exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}
