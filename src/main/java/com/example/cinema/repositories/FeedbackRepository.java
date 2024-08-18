package com.example.cinema.repositories;

import com.example.cinema.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
//    List<Feedback> findByUserId(Integer userId);
    @Query("SELECT f.type, COUNT(f), AVG(f.rated) FROM Feedback f GROUP BY f.type")
    List<Object[]> getFeedbackStatistics();

    List<Feedback> findByType(String type);
}
