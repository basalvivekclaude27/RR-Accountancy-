package com.rraccountancy.app.repository;

import com.rraccountancy.app.domain.BusinessTaxReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BusinessTaxReturnRepository
        extends JpaRepository<BusinessTaxReturn, Long>, JpaSpecificationExecutor<BusinessTaxReturn> {

    @Query("select distinct b.businessType from BusinessTaxReturn b order by b.businessType")
    List<String> findDistinctBusinessTypes();

    @Query("select distinct b.industry from BusinessTaxReturn b order by b.industry")
    List<String> findDistinctIndustries();

    @Query("select distinct b.taxYearStart from BusinessTaxReturn b order by b.taxYearStart desc")
    List<Integer> findDistinctTaxYears();
}
