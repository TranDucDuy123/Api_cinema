package com.example.cinema.controllers;

import com.example.cinema.dtos.request.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path =  "")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HomePageController {
//    @Autowired

    @GetMapping("/home")
    public ApiResponse<String> homePage() {
        ApiResponse<String> response = ApiResponse.<String>builder()
                .message("Success")
                .result("Welcome to HomePage")
                .build();
        return response;
    }
    @GetMapping("/contact")
    public ApiResponse<String> contactPage() {
        ApiResponse<String> response = ApiResponse.<String>builder()
                .message("Success")
                .result("Welcome to Contact Page")
                .build();
        return response;
    }

}
