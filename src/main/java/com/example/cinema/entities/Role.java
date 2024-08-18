package com.example.cinema.entities;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToMany
    Set<Permission> permissions;
}
