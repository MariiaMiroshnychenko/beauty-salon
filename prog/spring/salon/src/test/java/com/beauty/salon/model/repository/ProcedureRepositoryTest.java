package com.beauty.salon.model.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class ProcedureRepositoryTest {
    @Autowired
    private ProcedureRepository procedureRepository;

    @Test
    public void findProcedureByCodeAndLanguageId_LocaleTest() {
        Assert.assertEquals("hairdo",
                                        procedureRepository.findProcedureByCodeAndLanguageId_Locale(111 ,"en").getName());
    }

    @Test
    public void findProceduresByLanguageId_LocaleTest() {
        Assert.assertEquals(4, procedureRepository.findProceduresByLanguageId_Locale("en").size());
    }
}
