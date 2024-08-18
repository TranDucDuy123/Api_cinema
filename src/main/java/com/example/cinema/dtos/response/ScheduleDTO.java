package com.example.cinema.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {
    private Integer id;
    private LocalDate startDate;
    private LocalTime startTime;
    private Double price;
    private Integer movieId;
    private Integer roomId;
    private Integer branchId;
}

