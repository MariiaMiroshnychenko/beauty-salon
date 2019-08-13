package com.beauty.salon.model.service.impl;

import com.beauty.salon.model.entity.Email;
import com.beauty.salon.model.entity.Record;
import com.beauty.salon.model.entity.User;
import com.beauty.salon.model.repository.EmailRepository;
import com.beauty.salon.model.repository.ProcedureRepository;
import com.beauty.salon.model.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class EmailServiceImpl implements EmailService {
    private EmailRepository emailRepository;
    private ProcedureRepository procedureRepository;

    @Autowired
    public EmailServiceImpl(EmailRepository emailRepository,
                            ProcedureRepository procedureRepository) {
        this.emailRepository = emailRepository;
        this.procedureRepository = procedureRepository;
    }

    private String createEmailText(Record record, String locale) {
        record.setProcedureId(procedureRepository.findProcedureByCodeAndLanguageId_Locale(
                record.getProcedureId().getCode(), locale));

        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", new Locale(locale));

        StringBuilder emailTextBuilder = new StringBuilder();

        return emailTextBuilder
                .append(resourceBundle.getString("dear")).append(record.getClientId().getName()).append('!').append("\n")
                .append(record.getRecordDate()).append(" ").append(resourceBundle.getString("visited"))
                .append(record.getProcedureId().getName()).append("\". ")
                .append(resourceBundle.getString("leave.feedback")).toString();
    }

    @Override
    public void createEmail(Record record, String locale) {
        emailRepository.saveAndFlush(Email.builder()
                .clientId(record.getClientId())
                .text(createEmailText(record, locale))
                .recordId(record)
                .status(true)
                .build());
    }

    @Override
    public void updateEmailStatus(Integer notificationId) {
        Email email = emailRepository.getOne(notificationId);
        email.setStatus(false);
        emailRepository.saveAndFlush(email);
    }

    @Override
    public Page<Email> findEmailsByClient(User client, Pageable pageable) {
        return emailRepository.findEmailsByClientIdAndStatusIsTrue(client, pageable);
    }

    @Override
    public Integer clientEmailAmount(User client) {
        return emailRepository.countAllByClientIdAndStatusIsTrue(client);
    }
}
