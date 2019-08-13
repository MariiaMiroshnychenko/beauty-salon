package model.service.user;

import model.entity.Record;
import model.entity.User;

import java.time.LocalTime;
import java.util.Map;

public interface MasterService {
    Map<LocalTime, Record> masterScheduleMap(String date, User user, String language);
}
