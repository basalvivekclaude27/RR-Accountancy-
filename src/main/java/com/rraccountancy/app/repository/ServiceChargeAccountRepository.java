package com.rraccountancy.app.repository;

import com.rraccountancy.app.domain.ServiceChargeAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceChargeAccountRepository
        extends JpaRepository<ServiceChargeAccount, Long>, JpaSpecificationExecutor<ServiceChargeAccount> {

    @Query("select distinct s.propertyName from ServiceChargeAccount s order by s.propertyName")
    List<String> findDistinctPropertyNames();

    @Query("select distinct s.financialYearStart from ServiceChargeAccount s order by s.financialYearStart desc")
    List<Integer> findDistinctFinancialYears();
}
