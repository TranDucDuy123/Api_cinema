package com.example.cinema.controllers;

import com.example.cinema.dtos.request.ApiResponse;
import com.example.cinema.dtos.request.UserCreateRequest;
import com.example.cinema.dtos.request.UserUpdateRequest;
import com.example.cinema.dtos.response.UserResponse;
import com.example.cinema.entities.User;
import com.example.cinema.repositories.RoleRepository;
import com.example.cinema.services.UserSevice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserSevice usersevice;
    @Autowired
    RoleRepository repositoryRole;


    //get all
    @GetMapping("/getall")
   List<User> getAllUsers() {
        return (usersevice.getAll());
    }
    @GetMapping("/")
    String test() {
        return (usersevice.test());
    }

//    get id
    @GetMapping("/{userid}")
    ApiResponse<UserResponse> getUser(@PathVariable Integer userid) {
        return ApiResponse.<UserResponse>builder()
                .result(usersevice.getUser(userid))
                .build();
    }
//
    @PostMapping("/adduser")
    ApiResponse<UserResponse> addUser(@RequestBody @Valid UserCreateRequest request) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(usersevice.createUser(request));
        return apiResponse;
    }
    @PutMapping("/update/{userid}")
    ApiResponse<UserResponse> updateUser(@PathVariable Integer userId, @RequestBody @Valid UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(usersevice.updateUser(userId,request))
                .build();
    }

}
