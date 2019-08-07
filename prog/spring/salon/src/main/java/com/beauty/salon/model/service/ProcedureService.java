package com.beauty.salon.model.service;

import com.beauty.salon.model.entity.Procedure;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProcedureService {
    Procedure findProcedureById(Integer id);
    List<Procedure> findProceduresByLocale(String locale);
    Procedure findProcedureByCodeAndLocale(Integer procedureCode, String locale);
}
