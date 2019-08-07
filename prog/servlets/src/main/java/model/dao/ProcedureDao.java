package model.dao;

import model.entity.Procedure;

import java.util.List;

public interface ProcedureDao extends GenericDao<Procedure> {
    Procedure findProcedureById(Integer id);
    List<Procedure> findProceduresByLanguageId(Integer languageId);
    Procedure findProcedureByCodeAndLanguageId(Integer procedureCode, Integer languageId);
}
