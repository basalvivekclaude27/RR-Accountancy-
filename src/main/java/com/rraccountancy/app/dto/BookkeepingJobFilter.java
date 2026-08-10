package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.JobCategory;
import com.rraccountancy.app.domain.JobStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.YearMonth;

/** Query-string-bound filter state for the Bookkeeping Jobs list. */
public class BookkeepingJobFilter {

    private String client;
    private JobStatus status;
    @DateTimeFormat(pattern = "yyyy-MM")
    private YearMonth period;
    private JobCategory category;
    private String assignedTo;
    private String q;
    private int page = 0;

    public boolean hasClient() { return client != null && !client.isBlank(); }
    public boolean hasAssignedTo() { return assignedTo != null && !assignedTo.isBlank(); }
    public boolean hasQuery() { return q != null && !q.isBlank(); }

    public String getClient() { return client; }
    public void setClient(String client) { this.client = client; }

    public JobStatus getStatus() { return status; }
    public void setStatus(JobStatus status) { this.status = status; }

    public YearMonth getPeriod() { return period; }
    public void setPeriod(YearMonth period) { this.period = period; }

    public JobCategory getCategory() { return category; }
    public void setCategory(JobCategory category) { this.category = category; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    public String getQ() { return q; }
    public void setQ(String q) { this.q = q; }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = Math.max(page, 0); }
}
