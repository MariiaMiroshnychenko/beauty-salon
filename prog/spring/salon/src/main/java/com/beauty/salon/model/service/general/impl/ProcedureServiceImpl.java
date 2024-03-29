package com.beauty.salon.model.service.general.impl;

import com.beauty.salon.model.entity.Procedure;
import com.beauty.salon.model.repository.ProcedureRepository;
import com.beauty.salon.model.service.general.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcedureServiceImpl implements ProcedureService {
    private final ProcedureRepository procedureRepository;

    @Autowired
    public ProcedureServiceImpl(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }

    @Override
    public Procedure findProcedureById(Integer id) {
        return procedureRepository.getOne(id);
    }

    @Override
    public List<Procedure> findProceduresByLocale(String locale) {
        return procedureRepository.findProceduresByLanguageId_Locale(locale);
    }
}
