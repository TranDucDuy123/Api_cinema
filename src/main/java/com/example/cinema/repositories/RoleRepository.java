package com.example.cinema.repositories;

import com.example.cinema.entities.Role;
import com.example.cinema.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<User> findByName(String username);
}
