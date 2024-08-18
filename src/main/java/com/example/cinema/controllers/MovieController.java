package com.example.cinema.controllers;

import com.example.cinema.dtos.request.ApiResponse;
import com.example.cinema.entities.Movie;
import com.example.cinema.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {
    @Autowired
    MovieService movieService;

    @GetMapping("")
    public ApiResponse<List<Movie>> getMovies() {
        return ApiResponse.<List<Movie>>builder()
                .result(movieService.getMovies())
                .build();
    }
}