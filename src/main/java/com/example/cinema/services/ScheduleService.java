package com.example.cinema.services;

import com.example.cinema.dtos.request.ScheduleCreateRequest;
import com.example.cinema.dtos.response.ScheduleDTO;
import com.example.cinema.dtos.response.ScheduleStatisticsDTO;
import com.example.cinema.entities.Branch;
import com.example.cinema.entities.Movie;
import com.example.cinema.entities.Room;
import com.example.cinema.entities.Schedule;
import com.example.cinema.exceptions.AppException;
import com.example.cinema.exceptions.ErrorCode;
import com.example.cinema.repositories.BranchRepository;
import com.example.cinema.repositories.MovieRepository;
import com.example.cinema.repositories.RoomRepository;
import com.example.cinema.repositories.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BranchRepository branchRepository;


    public Schedule createSchedule(ScheduleCreateRequest request) {
        Schedule schedule = new Schedule();
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new AppException(ErrorCode.Movie_NOT_EXISTED));
        Branch branch = branchRepository.findById(request.getBranchId())
                .orElseThrow(() -> new AppException(ErrorCode.Brand_NOT_EXISTED));
        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(()-> new AppException(ErrorCode.Room_NOT_EXISTED));

        schedule.setMovie(movie);
        schedule.setRoom(room);
        schedule.setBranch(branch);
        schedule.setStartDate(request.getStartDate());
        schedule.setStartTime(request.getStartTime());
        schedule.setPrice(request.getPrice());

        return scheduleRepository.save(schedule);
    }

    public Schedule updateSchedule(Integer id, ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new RuntimeException("Schedule not found"));
        schedule.setStartDate(scheduleDTO.getStartDate());
        schedule.setStartTime(scheduleDTO.getStartTime());
        schedule.setPrice(scheduleDTO.getPrice());

        // Lấy movie, room, branch từ id và gán vào schedule
        schedule.setMovie(movieRepository.findById(scheduleDTO.getMovieId()).orElseThrow(() -> new RuntimeException("Movie not found")));
        schedule.setRoom(roomRepository.findById(scheduleDTO.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found")));
        schedule.setBranch(branchRepository.findById(scheduleDTO.getBranchId()).orElseThrow(() -> new RuntimeException("Branch not found")));

        return scheduleRepository.save(schedule);
    }

    public void deleteSchedule(Integer id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new RuntimeException("Schedule not found"));
        scheduleRepository.delete(schedule);
    }
//
//    public List<Schedule> getAllSchedules() {
//        return scheduleRepository.findAll();
//    }

//manager schedule end
    public List<Object[]> getSchedules() {
        return scheduleRepository.findAllSchedulesWithMovieAndRoom();
    }
    public List<Object[]> getSchedulesOpen() {
        return scheduleRepository.findAllSchedulesWithMovieAndRoomNative();
    }
//    Custom schedule
    public List<Object[]> getSchedulesWithDay(String search,LocalDate startDay, LocalDate endDay) {
        return scheduleRepository.findSchedulesWithinNextWeek(search, startDay, endDay);
    }

    public ScheduleStatisticsDTO getScheduleStatistics() {
        long totalSchedules = scheduleRepository.count();
        double averagePrice = scheduleRepository.findAveragePrice();
        double maxPrice = scheduleRepository.findMaxPrice();
        double minPrice = scheduleRepository.findMinPrice();

        return ScheduleStatisticsDTO.builder()
                .totalSchedules(totalSchedules)
                .averagePrice(averagePrice)
                .maxPrice(maxPrice)
                .minPrice(minPrice)
                .build();
    }
}
