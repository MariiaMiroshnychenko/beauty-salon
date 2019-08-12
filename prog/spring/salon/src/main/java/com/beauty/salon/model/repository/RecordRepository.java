package com.beauty.salon.model.repository;

import com.beauty.salon.model.entity.Record;
import com.beauty.salon.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Integer> {

    @Query(value = "SELECT * FROM `record_table` WHERE `record_date` = ?1 AND `master_id` = ?2", nativeQuery = true)
    List<Record> findRecordsByRecordDateAndMasterId(String date, Integer masterId);

    List<Record> findRecordsByMasterId(User masterId);

    List<Record> findRecordsByClientIdAndRecordDateBeforeOrRecordDateEqualsAndTimeBefore(User user, LocalDate dateBefore,
                                                                                         LocalDate dateEquals, LocalTime time);

    @Query(value = "SELECT * FROM `record_table` WHERE `record_date` = ?1 AND `time` = ?2", nativeQuery = true)
    List<Record> findRecordsByRecordDateAndTime(String date, LocalTime time);

    List<Record> findRecordsByRecordDate(LocalDate date);

    List<Record> findRecordsByClientIdAndRecordDateAfterOrRecordDateEqualsAndTimeAfter(User user, LocalDate dateAfter, LocalDate dateEquals, LocalTime time);
}
