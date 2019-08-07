package com.beauty.salon.model.repository;

import com.beauty.salon.model.entity.Record;
import com.beauty.salon.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Integer> {
    List<Record> findRecordsByRecordDateAndMasterId_Id(LocalDate date, Integer masterId);
    List<Record> findRecordsByMasterId(User masterId);
    List<Record> findRecordsByClientIdAndRecordDateBeforeOrRecordDateEqualsAndTimeBefore(User user, LocalDate dateBefore, LocalDate dateEquals, LocalTime time);
    List<Record> findRecordsByClientIdAndRecordDateAfterOrRecordDateEqualsAndTimeAfter(User user, LocalDate dateAfter, LocalDate dateEquals, LocalTime time);
    List<Record> findRecordsByRecordDateAndTime(LocalDate date, LocalTime time);
    List<Record> findRecordsByRecordDate(LocalDate date);
}
