package com.rraccountancy.app.repository;

import com.rraccountancy.app.domain.BookkeepingJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookkeepingJobRepository
        extends JpaRepository<BookkeepingJob, Long>, JpaSpecificationExecutor<BookkeepingJob> {

    @Query("select distinct j.clientName from BookkeepingJob j order by j.clientName")
    List<String> findDistinctClientNames();

    @Query("select distinct j.assignedTo from BookkeepingJob j order by j.assignedTo")
    List<String> findDistinctAssignees();
}
