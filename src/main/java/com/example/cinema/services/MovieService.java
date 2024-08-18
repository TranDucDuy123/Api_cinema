package com.example.cinema.services;

import com.example.cinema.entities.Movie;
import com.example.cinema.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }
}
