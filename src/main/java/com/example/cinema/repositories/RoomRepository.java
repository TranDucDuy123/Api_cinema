package com.example.cinema.repositories;

import com.example.cinema.entities.Permission;
import com.example.cinema.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
//    Optional<User> findByName(String username);
}
