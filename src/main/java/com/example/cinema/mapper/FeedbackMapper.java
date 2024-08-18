package com.example.cinema.mapper;

import com.example.cinema.dtos.request.FeedbackRequest;
import com.example.cinema.dtos.request.PermissionRequest;
import com.example.cinema.dtos.response.FeedbackResponse;
import com.example.cinema.dtos.response.PermissonResponse;
import com.example.cinema.entities.Feedback;
import com.example.cinema.entities.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {
    Feedback toFeedback(FeedbackRequest request);
    FeedbackResponse toFeedbackResponse(Feedback feedback);
}
