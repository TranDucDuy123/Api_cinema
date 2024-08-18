package com.example.cinema.dtos.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeedbackRequest {
    private Integer id;
    private String content;
    private Integer rated;
    private Integer referenceId;  // ID của phim hoặc dịch vụ mà phản hồi liên quan
}
