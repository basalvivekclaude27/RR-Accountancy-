package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.EnquirySource;
import com.rraccountancy.app.domain.StartupStage;
import com.rraccountancy.app.domain.StartupStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/** Query-string-bound filter state for the Startup Enquiries &amp; Progress list. */
public class StartupEnquiryFilter {

    private EnquirySource source;
    private StartupStage stage;
    private String businessType;
    private StartupStatus status;
    private String assignedTo;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateFrom;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateTo;

    private String q;
    private int page = 0;

    public boolean hasBusinessType() { return businessType != null && !businessType.isBlank(); }
    public boolean hasAssignedTo() { return assignedTo != null && !assignedTo.isBlank(); }
    public boolean hasQuery() { return q != null && !q.isBlank(); }

    public EnquirySource getSource() { return source; }
    public void setSource(EnquirySource source) { this.source = source; }

    public StartupStage getStage() { return stage; }
    public void setStage(StartupStage stage) { this.stage = stage; }

    public String getBusinessType() { return businessType; }
    public void setBusinessType(String businessType) { this.businessType = businessType; }

    public StartupStatus getStatus() { return status; }
    public void setStatus(StartupStatus status) { this.status = status; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    public LocalDate getDateFrom() { return dateFrom; }
    public void setDateFrom(LocalDate dateFrom) { this.dateFrom = dateFrom; }

    public LocalDate getDateTo() { return dateTo; }
    public void setDateTo(LocalDate dateTo) { this.dateTo = dateTo; }

    public String getQ() { return q; }
    public void setQ(String q) { this.q = q; }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = Math.max(page, 0); }
}
