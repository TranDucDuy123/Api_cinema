package com.example.cinema.repositories;

import com.example.cinema.entities.Branch;
import com.example.cinema.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {
//    Optional<User> findByName(String username);
}
