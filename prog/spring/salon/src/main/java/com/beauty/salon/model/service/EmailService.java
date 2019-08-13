package com.beauty.salon.model.service;

import com.beauty.salon.model.entity.Email;
import com.beauty.salon.model.entity.Record;
import com.beauty.salon.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmailService {
    void createEmail(Record record, String locale);
    void updateEmailStatus(Integer notificationId);
    Page<Email> findEmailsByClient(User client, Pageable pageable);
    Integer clientEmailAmount(User client);
}
