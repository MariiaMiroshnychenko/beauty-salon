package model.service.user;

import model.entity.Record;
import model.entity.Schedule;
import model.entity.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ClientService {
    List<LocalTime> availableTime(LocalDate date, String masterId, int beginHour, int endHour, int minute);
    List<User> masterList(LocalDate date);
    List<Record> futureOrPastRecords(User user, String language, String query);
    List<Record> uncheckedRecords(User user, String language);
}
