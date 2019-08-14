package com.beauty.salon.model.service;

import com.beauty.salon.model.entity.Procedure;
import com.beauty.salon.model.entity.Record;
import com.beauty.salon.model.entity.User;
import com.beauty.salon.model.repository.EmailRepository;
import com.beauty.salon.model.repository.ProcedureRepository;
import com.beauty.salon.model.service.general.impl.EmailServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class EmailServiceTest {

    @Mock
    private EmailRepository emailRepository;

    @Mock
    private ProcedureRepository procedureRepository;

    @InjectMocks
    private EmailServiceImpl emailService;

    @Spy
    private Record record;

    @Mock
    private Procedure procedure;

    @Mock
    private User user;


    @Test
    public void createEmailTextTest() {
        when(procedureRepository.findProcedureByCodeAndLanguageId_Locale(
                anyInt(), anyString())).thenReturn(procedure);
        when(record.getProcedureId()).thenReturn(procedure);
        when(procedure.getCode()).thenReturn(112);
        when(record.getProcedureId()).thenReturn(procedure);
        when(record.getClientId()).thenReturn(user);
        when(record.getClientId().getName()).thenReturn("Alina");
        when(record.getRecordDate()).thenReturn(LocalDate.of(2019, 7, 25));
        when(record.getProcedureId().getName()).thenReturn("hairdo");

        assertEquals("Dear Alina!" +
                        "\n2019-07-25 you have visited procedure \"hairdo\". Did you like it?" +
                        "\nPlease leave your feedback about it so that we make our service better!" +
                        "\nRegards," +
                        "\nbeauty_salon.",
                ReflectionTestUtils.invokeMethod(emailService, "createEmailText", record, "en"));
    }
}