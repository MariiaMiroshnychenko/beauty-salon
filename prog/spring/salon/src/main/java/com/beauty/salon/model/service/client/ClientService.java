package com.beauty.salon.model.service.client;

import java.time.LocalTime;
import java.util.List;

public interface ClientService {
    List<LocalTime> availableTimes(String date, Integer masterId, int beginHour, int endHour, int minute);
}
