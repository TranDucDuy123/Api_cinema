package com.example.cinema.mapper;

import com.example.cinema.dtos.request.RoleRequest;
import com.example.cinema.dtos.response.RoleResponse;
import com.example.cinema.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore =true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);
}
