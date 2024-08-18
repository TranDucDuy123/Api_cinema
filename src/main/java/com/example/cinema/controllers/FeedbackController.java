package com.example.cinema.controllers;

import com.example.cinema.dtos.request.ApiResponse;
import com.example.cinema.dtos.request.FeedbackRequest;
import com.example.cinema.dtos.response.FeedbackResponse;
import com.example.cinema.entities.Feedback;
import com.example.cinema.repositories.UserRepository;
import com.example.cinema.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add/{type}")
    private ApiResponse<FeedbackResponse> addFeedback(@PathVariable String type,@RequestBody FeedbackRequest feedbackRequest, @AuthenticationPrincipal UserDetails userDetails) {
        return ApiResponse.<FeedbackResponse>builder()
                .code(200)
                .message("Added feedback")
                .result(feedbackService.addFeedback(type,feedbackRequest))
                .build();
    }
    @GetMapping("/getall")
    private ApiResponse<List<Feedback>> getFeedback() {
        return  ApiResponse.<List<Feedback>>builder()
                .code(200)
                .message("Get all feedbacks")
                .result(feedbackService.getAll())
                .build();
    }

    @GetMapping("/type/{type}")
    public ApiResponse<List<Feedback>> getFeedbacksByType(@PathVariable String type) {
        return ApiResponse.<List<Feedback>>builder()
                .code(200)
                .message("Feedbacks fetched successfully")
                .result(feedbackService.getFeedbacksByType(type))
                .build();
    }
    @GetMapping("/statictics")
    public ApiResponse<List<Object[]>> getFeedbackStatistics() {
        List<Object[]> stats = feedbackService.getFeedbackStatistics();
        return ApiResponse.<List<Object[]>>builder()
                .code(200)
                .message("Feedback statistics fetched successfully")
                .result(stats)
                .build();
    }
}
