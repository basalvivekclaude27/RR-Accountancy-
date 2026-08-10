package com.rraccountancy.app.repository;

import com.rraccountancy.app.domain.BudgetForecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BudgetForecastRepository
        extends JpaRepository<BudgetForecast, Long>, JpaSpecificationExecutor<BudgetForecast> {

    @Query("select distinct b.clientName from BudgetForecast b order by b.clientName")
    List<String> findDistinctClientNames();

    @Query("select distinct b.financialYearStart from BudgetForecast b order by b.financialYearStart desc")
    List<Integer> findDistinctFinancialYears();
}
