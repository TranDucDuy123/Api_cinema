package com.example.cinema.dtos.request;

import com.example.cinema.dtos.response.RoleResponse;
import com.example.cinema.entities.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {
    @Size(min = 3, message = "Pass must be at least 3 characters")
    String username;

    @Size(min = 8, message = "Pass must be at least 8 characters")
    String password;

    String fullName;
    String phone;
//    Set<RoleResponse> roles;
}
