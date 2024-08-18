package com.example.cinema.repositories;

import com.example.cinema.entities.Feedback;
import com.example.cinema.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
