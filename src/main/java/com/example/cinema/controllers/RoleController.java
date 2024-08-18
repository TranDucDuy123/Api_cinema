package com.example.cinema.controllers;

import com.example.cinema.dtos.request.ApiResponse;
import com.example.cinema.dtos.request.PermissionRequest;
import com.example.cinema.dtos.request.RoleRequest;
import com.example.cinema.dtos.response.PermissonResponse;
import com.example.cinema.dtos.response.RoleResponse;
import com.example.cinema.services.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path =  "/role")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class RoleController {
    @Autowired
    RoleService roleService;

    @PostMapping()
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request){
        return  ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }
    @GetMapping()
    ApiResponse<List<RoleResponse>> getAll(){
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }
    @DeleteMapping("/{permission}")
    ApiResponse<Void> delete(@PathVariable Integer role){
        roleService.delete(role);
         return ApiResponse.<Void>builder().build();
    }

}
