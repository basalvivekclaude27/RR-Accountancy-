package com.rraccountancy.app.repository;

import com.rraccountancy.app.domain.VatReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface VatReturnRepository
        extends JpaRepository<VatReturn, Long>, JpaSpecificationExecutor<VatReturn> {

    @Query("select distinct v.clientType from VatReturn v order by v.clientType")
    List<String> findDistinctClientTypes();

    @Query("select distinct v.schemeType from VatReturn v order by v.schemeType")
    List<String> findDistinctSchemeTypes();

    @Query("select distinct v.periodStart from VatReturn v order by v.periodStart desc")
    List<LocalDate> findDistinctPeriods();
}
