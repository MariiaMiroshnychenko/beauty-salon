package com.beauty.salon.model.service.admin;

import com.beauty.salon.model.entity.Feedback;
import com.beauty.salon.model.entity.Record;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface AdminService {
    List<Feedback> findFeedbacksByMasterId(Integer masterId, String locale);
    Map<LocalTime, List<Record>> sortedRecordsByTime(String date, String locale);
}
