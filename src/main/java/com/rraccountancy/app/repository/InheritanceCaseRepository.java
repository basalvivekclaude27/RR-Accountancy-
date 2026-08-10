package com.rraccountancy.app.repository;

import com.rraccountancy.app.domain.InheritanceCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InheritanceCaseRepository
        extends JpaRepository<InheritanceCase, Long>, JpaSpecificationExecutor<InheritanceCase> {

    @Query("select distinct i.clientType from InheritanceCase i order by i.clientType")
    List<String> findDistinctClientTypes();

    @Query("select distinct i.planningType from InheritanceCase i order by i.planningType")
    List<String> findDistinctPlanningTypes();

    @Query("select distinct i.taxYearStart from InheritanceCase i order by i.taxYearStart desc")
    List<Integer> findDistinctTaxYears();
}
