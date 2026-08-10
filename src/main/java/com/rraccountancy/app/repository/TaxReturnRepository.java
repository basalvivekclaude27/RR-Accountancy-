package com.rraccountancy.app.repository;

import com.rraccountancy.app.domain.TaxReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaxReturnRepository
        extends JpaRepository<TaxReturn, Long>, JpaSpecificationExecutor<TaxReturn> {

    @Query("select distinct t.clientType from TaxReturn t order by t.clientType")
    List<String> findDistinctClientTypes();

    @Query("select distinct t.assignedTo from TaxReturn t order by t.assignedTo")
    List<String> findDistinctAssignees();

    @Query("select distinct t.taxYearStart from TaxReturn t order by t.taxYearStart desc")
    List<Integer> findDistinctTaxYears();
}
