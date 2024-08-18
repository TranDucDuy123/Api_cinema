package com.example.cinema.controllers;

import com.example.cinema.dtos.request.ApiResponse;
import com.example.cinema.dtos.request.PermissionRequest;
import com.example.cinema.dtos.response.PermissonResponse;
import com.example.cinema.services.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path =  "/permisson")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class PermissonController {
    @Autowired
    PermissionService permissionService;

    @PostMapping()
    ApiResponse<PermissonResponse> create(@RequestBody PermissionRequest request){
        return  ApiResponse.<PermissonResponse>builder()
                .result(permissionService.create(request))
                .build();
    }
    @GetMapping()
    ApiResponse<List<PermissonResponse>> getAll(){
        return ApiResponse.<List<PermissonResponse>>builder()
                .result(permissionService.getAll())
                .build();
    }
    @DeleteMapping("/{permission}")
    ApiResponse<Void> delete(@PathVariable Integer permission){
         permissionService.delete(permission);
         return ApiResponse.<Void>builder().build();
    }

}
