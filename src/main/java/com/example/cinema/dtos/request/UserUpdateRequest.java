package com.example.cinema.dtos.request;

import com.example.cinema.dtos.response.RoleResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String username;
    String password;
    String fullName;
    String phone;
    List<Integer> roles;
}
