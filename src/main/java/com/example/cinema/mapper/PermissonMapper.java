package com.example.cinema.mapper;

import com.example.cinema.dtos.request.PermissionRequest;
import com.example.cinema.dtos.response.PermissonResponse;
import com.example.cinema.entities.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissonMapper {
    Permission toPermisson(PermissionRequest request);
    PermissonResponse toPermissonResponse(Permission permission);
}
