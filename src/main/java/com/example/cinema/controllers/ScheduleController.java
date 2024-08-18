package com.example.cinema.controllers;

import com.example.cinema.dtos.request.ApiResponse;
import com.example.cinema.dtos.request.ScheduleCreateRequest;
import com.example.cinema.dtos.response.ScheduleDTO;
import com.example.cinema.entities.Schedule;
import com.example.cinema.services.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @GetMapping("")
    public ApiResponse<List<Object[]>> getMovies() {
        return ApiResponse.<List<Object[]>>builder()
                .code(200)
                .result(scheduleService.getSchedules())
                .build();
    }
    @GetMapping("/open")
    public ApiResponse<List<Object[]>> getMoviesOpen() {
        return ApiResponse.<List<Object[]>>builder()
                .code(200)
                .message("Schedule all")
                .result(scheduleService.getSchedulesOpen())
                .build();
    }
    @GetMapping("/schedulefilter")
    public ApiResponse<List<Object[]>> getScheduleFilter(@RequestParam ("search") String search ,@RequestParam("startDay")LocalDate startDay, @RequestParam("endDay") LocalDate endDay) {
        return ApiResponse.<List<Object[]>>builder()
                .code(200)
                .message("Schedule next week")
                .result(scheduleService.getSchedulesWithDay(search, startDay, endDay))
                .build();
    }

    @PostMapping("/add")
    public Schedule createSchedule(@RequestBody ScheduleCreateRequest request) {
            return scheduleService.createSchedule(request);
    }
    @PutMapping("/{id}")
    public ApiResponse<Schedule> updateSchedule(@PathVariable Integer id, @RequestBody ScheduleDTO scheduleDTO) {
        return ApiResponse.<Schedule>builder()
                .code(200)
                .message(" Up Schedule " + id + " ---- Success")
                .result(scheduleService.updateSchedule(id, scheduleDTO))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Integer id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }


//    manager
//    @PostMapping("/create")
//    public ApiResponse<ScheduleResponse> createSchedule(@RequestBody ScheduleRequest request) {
//        Schedule schedule = new Schedule();
//        schedule.setMovieId(request.getMovieId());
//        schedule.setBranchId(request.getBranchId());
//        schedule.setRoomId(request.getRoomId());
//        schedule.setStartDate(request.getStartDate());
//        schedule.setStartTime(request.getStartTime());
//        schedule.setPrice(request.getPrice());
//        Schedule createdSchedule = scheduleService.createSchedule(schedule);
//        return ApiResponse.<ScheduleResponse>builder()
//                .code(200)
//                .message("Schedule created successfully")
//                .result(toScheduleResponse(createdSchedule))
//                .build();
//    }
}