package com.example.cinema.repositories;

import com.example.cinema.entities.Permission;
import com.example.cinema.entities.Role;
import com.example.cinema.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
//    Optional<User> findByName(String username);
}
