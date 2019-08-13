package model.service.impl;

import model.dao.FactoryDao;
import model.dao.RecordDao;
import model.entity.Record;
import model.service.RecordService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class RecordServiceImpl implements RecordService {
    @Override
    public void create(Record record) {
        RecordDao recordDao = FactoryDao.getInstance().recordDao();

        recordDao.create(record);
        recordDao.close();
    }

    @Override
    public List<Record> findRecordsByDateAndMasterId(LocalDate date, Integer masterId) {
        RecordDao recordDao = FactoryDao.getInstance().recordDao();
        List<Record> records = recordDao.findRecordsByDateAndMasterId(date, masterId);

        recordDao.close();
        return records;
    }

    @Override
    public List<Record> findRecordsByUserId(Integer userId, String userRole) {
        RecordDao recordDao = FactoryDao.getInstance().recordDao();
        List<Record> records = recordDao.findRecordsByUserId(userId, userRole);

        recordDao.close();
        return records;
    }

    @Override
    public List<Record> findRecordsByDateAndTimeWithCondition(LocalDate date, LocalTime time, String query) {
        RecordDao recordDao = FactoryDao.getInstance().recordDao();
        List<Record> records = recordDao.findRecordsByDateAndTimeWithCondition(date, time, query);

        recordDao.close();
        return records;
    }

    @Override
    public List<Record> findRecordsByDate(LocalDate date) {
        RecordDao recordDao = FactoryDao.getInstance().recordDao();
        List<Record> records = recordDao.findRecordsByDate(date);

        recordDao.close();
        return records;
    }

    @Override
    public List<Record> findRecordsByDateAndTime(LocalDate date, LocalTime time) {
        RecordDao recordDao = FactoryDao.getInstance().recordDao();
        List<Record> records = recordDao.findRecordsByDateAndTime(date, time);

        recordDao.close();
        return records;
    }

    @Override
    public List<Record> executeQuery(String query, Object... parameters) {
        RecordDao recordDao = FactoryDao.getInstance().recordDao();
        List<Record> records = recordDao.executeQuery(query, parameters);

        recordDao.close();
        return records;
    }

    @Override
    public Record findRecordById(Integer recordId) {
        RecordDao recordDao = FactoryDao.getInstance().recordDao();
        Record record = recordDao.findRecordById(recordId);

        recordDao.close();
        return record;
    }
}
