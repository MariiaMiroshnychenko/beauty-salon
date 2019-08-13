package com.beauty.salon.model.service.admin;

import com.beauty.salon.model.entity.Feedback;
import com.beauty.salon.model.entity.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface AdminService {
    Page<Feedback> findFeedbacksByMasterId(Integer masterId, String locale, Pageable pageable);
    Map<LocalTime, List<Record>> sortedRecordsByTime(LocalDate date, String locale);
}
