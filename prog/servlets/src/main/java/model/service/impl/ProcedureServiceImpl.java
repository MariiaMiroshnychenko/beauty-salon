package model.service.impl;

import model.dao.FactoryDao;
import model.dao.ProcedureDao;
import model.entity.Procedure;
import model.service.ProcedureService;

import java.util.List;

public class ProcedureServiceImpl implements ProcedureService {
    @Override
    public Procedure findProcedureById(Integer id) {
        ProcedureDao procedureDao = FactoryDao.getInstance().procedureDao();
        Procedure procedure = procedureDao.findProcedureById(id);

        procedureDao.close();
        return procedure;
    }

    @Override
    public List<Procedure> findProceduresByLanguageId(Integer languageId) {
        ProcedureDao procedureDao = FactoryDao.getInstance().procedureDao();
        List<Procedure> procedures = procedureDao.findProceduresByLanguageId(languageId);

        procedureDao.close();
        return procedures;
    }

    @Override
    public Procedure findProcedureByCodeAndLanguageId(Integer procedureCode, Integer languageId) {
        ProcedureDao procedureDao = FactoryDao.getInstance().procedureDao();
        Procedure procedure = procedureDao.findProcedureByCodeAndLanguageId(procedureCode, languageId);

        procedureDao.close();
        return procedure;
    }
}
