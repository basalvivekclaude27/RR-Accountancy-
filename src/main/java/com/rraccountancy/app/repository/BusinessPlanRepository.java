package com.rraccountancy.app.repository;

import com.rraccountancy.app.domain.BusinessPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BusinessPlanRepository
        extends JpaRepository<BusinessPlan, Long>, JpaSpecificationExecutor<BusinessPlan> {

    @Query("select distinct b.clientName from BusinessPlan b order by b.clientName")
    List<String> findDistinctClientNames();

    @Query("select distinct b.planType from BusinessPlan b order by b.planType")
    List<String> findDistinctPlanTypes();

    @Query("select distinct b.industry from BusinessPlan b order by b.industry")
    List<String> findDistinctIndustries();
}
