package com.rraccountancy.app.repository;

import com.rraccountancy.app.domain.PayrollRun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PayrollRunRepository
        extends JpaRepository<PayrollRun, Long>, JpaSpecificationExecutor<PayrollRun> {

    @Query("select distinct r.clientName from PayrollRun r order by r.clientName")
    List<String> findDistinctClientNames();

    @Query("select distinct r.assignedTo from PayrollRun r order by r.assignedTo")
    List<String> findDistinctAssignees();
}
