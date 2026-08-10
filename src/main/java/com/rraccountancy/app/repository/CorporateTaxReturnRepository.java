package com.rraccountancy.app.repository;

import com.rraccountancy.app.domain.CorporateTaxReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CorporateTaxReturnRepository
        extends JpaRepository<CorporateTaxReturn, Long>, JpaSpecificationExecutor<CorporateTaxReturn> {

    @Query("select distinct c.clientType from CorporateTaxReturn c order by c.clientType")
    List<String> findDistinctClientTypes();

    @Query("select distinct c.industry from CorporateTaxReturn c order by c.industry")
    List<String> findDistinctIndustries();
}
