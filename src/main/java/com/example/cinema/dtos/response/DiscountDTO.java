package com.example.cinema.dtos.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DiscountDTO {
    private String code;
    private double percentage;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isActive;
}
