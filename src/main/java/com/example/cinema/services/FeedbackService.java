package com.example.cinema.services;

import com.example.cinema.dtos.request.FeedbackRequest;
import com.example.cinema.dtos.response.FeedbackResponse;
import com.example.cinema.entities.Feedback;
import com.example.cinema.entities.User;
import com.example.cinema.exceptions.AppException;
import com.example.cinema.exceptions.ErrorCode;
import com.example.cinema.mapper.FeedbackMapper;
import com.example.cinema.repositories.FeedbackRepository;
import com.example.cinema.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    @Autowired
    FeedbackRepository feedbackRepository;
    @Autowired
    UserRepository userRepository;

    FeedbackMapper mapper;

    public FeedbackResponse addFeedback(String type,FeedbackRequest request) {
        var authen = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findById(Integer.parseInt(authen.getName()))
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Feedback feedback = new Feedback();
        switch (type){
            case "movie":
                feedback.setType("movie");
                feedback.setContent(request.getContent());
                feedback.setRated(request.getRated());
                feedback.setUserId(user.getId());
                feedback.setReferenceId(request.getReferenceId());
                if (request.getReferenceId() == null){
                    throw new AppException(ErrorCode.MIX_FIELD_FEEDBACK);
                }
                break;
            case "film":
                feedback.setType("film");
                feedback.setContent(request.getContent());
                feedback.setRated(request.getRated());
                feedback.setUserId(user.getId());
                feedback.setReferenceId(request.getReferenceId());
                if (request.getReferenceId() == null){
                    throw new AppException(ErrorCode.MIX_FIELD_FEEDBACK);
                }
                break;
            case "service":
                feedback.setType("service");
                feedback.setContent(request.getContent());
                feedback.setRated(request.getRated());
                feedback.setUserId(user.getId());
                break;
            case "web":
                feedback.setType("web");
                feedback.setContent(request.getContent());
                feedback.setRated(request.getRated());
                feedback.setUserId(user.getId());
                break;
            default:
                throw new AppException(ErrorCode.MIX_FIELD_FEEDBACK);
        }
        feedbackRepository.save(feedback);
        FeedbackResponse feedbackResponse = new FeedbackResponse().builder()
                .id(feedback.getId())
                .content(request.getContent())
                .type(type)
                .rated(request.getRated())
                .build();
        return feedbackResponse;
    }
    public List<Feedback> getAll(){
        return feedbackRepository.findAll();
//        var result = feedbackRepository.findAll();
//        return result.stream().map(mapper::toFeedbackResponse).toList();
    }
//   fype feedback
    public List<Feedback> getFeedbacksByType(String type) {
        return feedbackRepository.findByType(type);
    }
//    statictics
    public List<Object[]> getFeedbackStatistics() {
       return feedbackRepository.getFeedbackStatistics();
    }

}
