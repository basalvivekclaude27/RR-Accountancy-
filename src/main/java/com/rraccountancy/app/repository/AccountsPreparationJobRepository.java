package com.rraccountancy.app.repository;

import com.rraccountancy.app.domain.AccountsPreparationJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountsPreparationJobRepository
        extends JpaRepository<AccountsPreparationJob, Long>, JpaSpecificationExecutor<AccountsPreparationJob> {

    @Query("select distinct j.clientName from AccountsPreparationJob j order by j.clientName")
    List<String> findDistinctClientNames();

    @Query("select distinct j.assignedTo from AccountsPreparationJob j order by j.assignedTo")
    List<String> findDistinctAssignees();

    @Query("select distinct j.financialYearStart from AccountsPreparationJob j order by j.financialYearStart desc")
    List<Integer> findDistinctFinancialYears();
}
