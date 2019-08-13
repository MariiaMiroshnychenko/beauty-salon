package model.service.user;

import model.entity.Feedback;
import model.entity.Record;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface AdminService {
    List<Feedback> feedbackForMaster(Integer masterId, String language);
    Map<LocalTime, List<Record>> sortedRecordsByTime(LocalDate date, String locale);
}
