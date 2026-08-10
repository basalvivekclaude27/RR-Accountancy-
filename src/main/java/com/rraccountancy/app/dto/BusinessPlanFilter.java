package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.BusinessPlanStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/** Query-string-bound filter state for the Business Plans Overview list. */
public class BusinessPlanFilter {

    private String client;
    private String planType;
    private BusinessPlanStatus status;
    private String industry;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateFrom;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateTo;

    private String q;
    private int page = 0;

    public boolean hasClient() { return client != null && !client.isBlank(); }
    public boolean hasPlanType() { return planType != null && !planType.isBlank(); }
    public boolean hasIndustry() { return industry != null && !industry.isBlank(); }
    public boolean hasQuery() { return q != null && !q.isBlank(); }

    public String getClient() { return client; }
    public void setClient(String client) { this.client = client; }

    public String getPlanType() { return planType; }
    public void setPlanType(String planType) { this.planType = planType; }

    public BusinessPlanStatus getStatus() { return status; }
    public void setStatus(BusinessPlanStatus status) { this.status = status; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }

    public LocalDate getDateFrom() { return dateFrom; }
    public void setDateFrom(LocalDate dateFrom) { this.dateFrom = dateFrom; }

    public LocalDate getDateTo() { return dateTo; }
    public void setDateTo(LocalDate dateTo) { this.dateTo = dateTo; }

    public String getQ() { return q; }
    public void setQ(String q) { this.q = q; }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = Math.max(page, 0); }
}
