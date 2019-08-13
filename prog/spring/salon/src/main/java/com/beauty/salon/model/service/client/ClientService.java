package com.beauty.salon.model.service.client;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ClientService {
    List<LocalTime> availableTimes(LocalDate date, Integer masterId, int beginHour, int endHour, int minute);
}
