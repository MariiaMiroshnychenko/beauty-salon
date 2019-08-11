package com.beauty.salon.model.repository;

import com.beauty.salon.model.entity.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcedureRepository extends JpaRepository<Procedure, Integer> {
    List<Procedure> findProceduresByLanguageId_Locale(String locale);
    Procedure findProcedureByCodeAndLanguageId_Locale(Integer procedureCode, String locale);
}
