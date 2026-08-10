package com.rraccountancy.app.repository;

import com.rraccountancy.app.domain.FinancialReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FinancialReportRepository
        extends JpaRepository<FinancialReport, Long>, JpaSpecificationExecutor<FinancialReport> {

    @Query("select distinct r.clientName from FinancialReport r order by r.clientName")
    List<String> findDistinctClientNames();

    @Query("select distinct r.financialYearStart from FinancialReport r order by r.financialYearStart desc")
    List<Integer> findDistinctFinancialYears();
}
