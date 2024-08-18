package com.example.cinema.controllers;

import com.example.cinema.configuration.SecurityConfig;
import com.example.cinema.dtos.request.AuthenticationRequest;
import com.example.cinema.dtos.request.ApiResponse;
import com.example.cinema.dtos.request.IntrospectRequest;
import com.example.cinema.dtos.response.AuthenticationResponse;
import com.example.cinema.dtos.response.IntrospectResponse;
import com.example.cinema.dtos.response.MyUser;
import com.example.cinema.services.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;
    SecurityConfig securityConfig;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result =  authenticationService.authenticate(request);
           return ApiResponse.<AuthenticationResponse>builder()
                   .code(200)
                   .message("login succses")
                   .result(result)
                   .build();
    }
    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> autheticate(@RequestBody IntrospectRequest request) throws JOSEException, ParseException {
            var result = authenticationService.introspect(request);
            return ApiResponse.<IntrospectResponse>builder()
                    .result(result)
                    .build();
    }
    @GetMapping("/information")
    public String getUserInformation() {
        System.out.println("asa");
        String result = SecurityConfig.getPrincipal().getUsername();
        return result;
    }
}
