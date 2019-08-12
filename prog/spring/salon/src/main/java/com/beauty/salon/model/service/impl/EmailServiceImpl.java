package com.beauty.salon.model.service.impl;

import com.beauty.salon.model.entity.Email;
import com.beauty.salon.model.entity.Record;
import com.beauty.salon.model.entity.User;
import com.beauty.salon.model.repository.EmailRepository;
import com.beauty.salon.model.repository.ProcedureRepository;
import com.beauty.salon.model.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        StringBuilder emailTextBuilder = new StringBuilder();

        return emailTextBuilder
                .append("dear").append(record.getClientId().getName()).append('!').append("\n")
                .append(record.getRecordDate()).append(" ").append("visited")
                .append(record.getProcedureId().getName()).append(". ")
                .append("leave.feedback").toString();
    }

    @Override
    public Email createEmail(Record record, String locale) {
        return Email.builder()
                .clientId(record.getClientId())
                .text(createEmailText(record, locale))
                .recordId(record)
                .status(true)
                .build();
    }

    @Override
    public void saveEmail(Email email) {
        emailRepository.saveAndFlush(email);
    }

    @Override
    public List<Email> findEmailsByClient(User client) {
        return emailRepository.findEmailsByClientIdAndStatusIsTrue(client);
    }

    @Override
    public Integer clientEmailAmount(User client) {
        return emailRepository.countAllByClientIdAndStatusIsTrue(client);
    }
}
