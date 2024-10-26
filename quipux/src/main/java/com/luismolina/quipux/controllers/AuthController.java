package com.luismolina.quipux.controllers;

import com.luismolina.quipux.auth.JwtService;
import com.luismolina.quipux.database.entity.User;
import com.luismolina.quipux.dtos.LoginUserDto;
import com.luismolina.quipux.dtos.RegisterUserDto;
import com.luismolina.quipux.models.LoginResponse;
import com.luismolina.quipux.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {
    /*
    @GetMapping("loginTest")
    public String loginTest() {
        return "Hello User";
    }
     */


    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = LoginResponse.builder().token(jwtToken).expiresIn(jwtService.getExpirationTime()).build();
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }
}
