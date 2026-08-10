package com.rraccountancy.app.repository;

import com.rraccountancy.app.domain.StartupEnquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StartupEnquiryRepository
        extends JpaRepository<StartupEnquiry, Long>, JpaSpecificationExecutor<StartupEnquiry> {

    @Query("select distinct s.businessType from StartupEnquiry s order by s.businessType")
    List<String> findDistinctBusinessTypes();

    @Query("select distinct s.assignedTo from StartupEnquiry s order by s.assignedTo")
    List<String> findDistinctAssignees();
}
