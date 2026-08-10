package com.rraccountancy.app.repository;

import com.rraccountancy.app.domain.CompanyFormationEnquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyFormationEnquiryRepository
        extends JpaRepository<CompanyFormationEnquiry, Long>, JpaSpecificationExecutor<CompanyFormationEnquiry> {

    @Query("select distinct c.companyType from CompanyFormationEnquiry c order by c.companyType")
    List<String> findDistinctCompanyTypes();

    @Query("select distinct c.jurisdiction from CompanyFormationEnquiry c order by c.jurisdiction")
    List<String> findDistinctJurisdictions();
}
