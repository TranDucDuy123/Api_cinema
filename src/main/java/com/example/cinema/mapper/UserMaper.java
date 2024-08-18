package com.example.cinema.mapper;

import com.example.cinema.dtos.request.UserCreateRequest;
import com.example.cinema.dtos.request.UserUpdateRequest;
import com.example.cinema.dtos.response.UserResponse;
import com.example.cinema.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMaper {
   User toUser(UserCreateRequest request);
   @Mapping(target="roles",ignore=true)
   UserResponse toUserResponse(User user);

   @Mapping(target="roles",ignore=true)
   void updateUser(@MappingTarget User user, UserUpdateRequest request);
}

