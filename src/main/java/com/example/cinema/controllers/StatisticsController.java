package com.example.cinema.controllers;

import com.example.cinema.dtos.request.ApiResponse;
import com.example.cinema.dtos.request.ScheduleCreateRequest;
import com.example.cinema.dtos.response.DiscountStatisticsDTO;
import com.example.cinema.dtos.response.ScheduleDTO;
import com.example.cinema.dtos.response.ScheduleStatisticsDTO;
import com.example.cinema.entities.Schedule;
import com.example.cinema.services.DiscountService;
import com.example.cinema.services.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatisticsController {
    @Autowired
    DiscountService discountService;
    @Autowired
    ScheduleService scheduleService;

    @GetMapping("/discounts")
    public ApiResponse<DiscountStatisticsDTO> getDiscountStatistics() {
        return ApiResponse.<DiscountStatisticsDTO>builder()
                .code(200)
                .message("Statistics Discount")
                .result(discountService.getDiscountStatistics())
                .build();
    }

    @GetMapping("/schedules")
    public ApiResponse<ScheduleStatisticsDTO> getScheduleStatistics() {
        return ApiResponse.<ScheduleStatisticsDTO>builder()
                .code(200)
                .message("Statistics Schedule")
                .result(scheduleService.getScheduleStatistics())
                .build();
    }
}
