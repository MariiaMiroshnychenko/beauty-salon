package model.service;

import model.entity.Procedure;

import java.util.List;

public interface ProcedureService {
    Procedure findProcedureById(Integer id);
    List<Procedure> findProceduresByLanguageId(Integer languageId);
    Procedure findProcedureByCodeAndLanguageId(Integer procedureCode, Integer languageId);
}
