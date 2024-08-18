package com.example.cinema.repositories;

import com.example.cinema.dtos.response.ScheduleDTO;
import com.example.cinema.entities.Feedback;
import com.example.cinema.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
//    List<Feedback> findByUserId(Integer userId);
    @Query("SELECT s.id, s.movie.name, s.room.name, s.startDate, s.startTime, s.price FROM Schedule s")
    List<Object[]> findAllSchedulesWithMovieAndRoom();

    @Query(value = "SELECT s.id, m.name as movieName, r.name as roomName, s.start_date, s.start_time, s.price " +
            "FROM schedule s " +
            "JOIN movie m ON s.movie_id = m.id " +
            "JOIN room r ON s.room_id = r.id"+
            "JOIN ticket t ON s.id = t.schedule_id", nativeQuery = true)
    List<Object[]> findAllSchedulesWithMovieAndRoomNative();

    @Query("SELECT s, m.name FROM Schedule s " +
            "JOIN s.movie m " +
            "WHERE m.name LIKE CONCAT('%', :search, '%') " +
            "AND s.startDate BETWEEN :startDate AND :endDate")
    List<Object[]> findSchedulesWithinNextWeek(@Param("search") String search,
                                               @Param("startDate") LocalDate startDate,
                                               @Param("endDate") LocalDate endDate);
//    @Query("SELECT s,m.name FROM Schedule s " +
//            "JOIN movie m ON m.name LIKE CONCAT('%',:search,'%')" +
//            "WHERE s.startDate BETWEEN :startDate AND :endDate")
//    List<Object[]> findSchedulesWithinNextWeek(@Param("search") String search,@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT AVG(s.price) FROM Schedule s")
    double findAveragePrice();

    @Query("SELECT MAX(s.price) FROM Schedule s")
    double findMaxPrice();

    @Query("SELECT MIN(s.price) FROM Schedule s")
    double findMinPrice();
}
