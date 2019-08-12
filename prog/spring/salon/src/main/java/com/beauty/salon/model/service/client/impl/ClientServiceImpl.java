package com.beauty.salon.model.service.client.impl;

import com.beauty.salon.model.entity.Record;
import com.beauty.salon.model.repository.RecordRepository;
import com.beauty.salon.model.service.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    private RecordRepository recordRepository;

    @Autowired
    public ClientServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    private List<LocalTime> unavailableTimeList(String date, Integer masterId) {
        List<LocalTime> unavailableTimes = new ArrayList<>();
        List<Record> recordList = recordRepository.findRecordsByRecordDateAndMasterId(date, masterId);

        recordList.forEach(record -> unavailableTimes.add(record.getTime()));

        return unavailableTimes;
    }

    private List<LocalTime> workingTimeList(int beginHour, int endHour, int minute) {
        List<LocalTime> timeList = new ArrayList<>();

        LocalTime availableTime;
        while (beginHour <= endHour) {
            availableTime = LocalTime.of(beginHour, minute);
            timeList.add(availableTime);
            beginHour++;
        }

        return timeList;
    }

    @Override
    public List<LocalTime> availableTimes(String date, Integer masterId, int beginHour, int endHour, int minute) {
        return workingTimeList(beginHour, endHour, minute)
                .stream().filter(time -> !unavailableTimeList(date, masterId).contains(time) ||
                        LocalDate.parse(date).equals(LocalDate.now()) &&
                                time.compareTo(LocalTime.now()) > 0)
                .collect(Collectors.toList());
    }
}
