package com.beauty.salon.model.repository;

import com.beauty.salon.model.entity.Schedule;
import com.beauty.salon.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ScheduleRepository extends JpaRepository<Schedule, User> {
    List<Schedule> findSchedulesByMondayIsTrue();
    List<Schedule> findSchedulesByTuesdayIsTrue();
    List<Schedule> findSchedulesByWednesdayIsTrue();
    List<Schedule> findSchedulesByThursdayIsTrue();
    List<Schedule> findSchedulesByFridayIsTrue();
    List<Schedule> findSchedulesBySaturdayIsTrue();
    List<Schedule> findSchedulesBySundayIsTrue();
}
