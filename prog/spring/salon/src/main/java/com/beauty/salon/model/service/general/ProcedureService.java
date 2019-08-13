package com.beauty.salon.model.service.general;

import com.beauty.salon.model.entity.Procedure;

import java.util.List;

public interface ProcedureService {
    Procedure findProcedureById(Integer id);
    List<Procedure> findProceduresByLocale(String locale);
}
