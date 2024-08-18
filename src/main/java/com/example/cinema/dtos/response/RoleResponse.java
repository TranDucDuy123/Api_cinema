package com.example.cinema.dtos.response;

import com.example.cinema.entities.Permission;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse {
    Integer id;
    String name;
    Set<Permission> permissions;
}
