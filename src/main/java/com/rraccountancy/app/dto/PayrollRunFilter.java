package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.PayrollFrequency;
import com.rraccountancy.app.domain.PayrollStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.YearMonth;

/** Query-string-bound filter state for the Payroll Runs list. */
public class PayrollRunFilter {

    private String client;
    private PayrollFrequency frequency;

    @DateTimeFormat(pattern = "yyyy-MM")
    private YearMonth month;

    private PayrollStatus status;
    private String assignedTo;
    private String q;
    private int page = 0;

    public boolean hasClient() { return client != null && !client.isBlank(); }
    public boolean hasAssignedTo() { return assignedTo != null && !assignedTo.isBlank(); }
    public boolean hasQuery() { return q != null && !q.isBlank(); }

    public String getClient() { return client; }
    public void setClient(String client) { this.client = client; }

    public PayrollFrequency getFrequency() { return frequency; }
    public void setFrequency(PayrollFrequency frequency) { this.frequency = frequency; }

    public YearMonth getMonth() { return month; }
    public void setMonth(YearMonth month) { this.month = month; }

    public PayrollStatus getStatus() { return status; }
    public void setStatus(PayrollStatus status) { this.status = status; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    public String getQ() { return q; }
    public void setQ(String q) { this.q = q; }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = Math.max(page, 0); }
}
