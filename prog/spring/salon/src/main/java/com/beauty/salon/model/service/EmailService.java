package com.beauty.salon.model.service;

import com.beauty.salon.model.entity.Email;
import com.beauty.salon.model.entity.Record;
import com.beauty.salon.model.entity.User;

import java.util.List;

public interface EmailService {
    Email createEmail(Record record, String locale);
    void saveEmail(Email email);
    List<Email> findEmailsByClient(User client);
    Integer clientEmailAmount(User client);
}
