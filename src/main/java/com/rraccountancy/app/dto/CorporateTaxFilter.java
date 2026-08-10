package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.TaxReturnStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/** Query-string-bound filter state for the Corporate Tax Overview list. */
public class CorporateTaxFilter {

    private String clientType;
    private TaxReturnStatus status;
    private String industry;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dueFrom;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dueTo;

    private String q;
    private int page = 0;

    public boolean hasClientType() { return clientType != null && !clientType.isBlank(); }
    public boolean hasIndustry() { return industry != null && !industry.isBlank(); }
    public boolean hasQuery() { return q != null && !q.isBlank(); }

    public String getClientType() { return clientType; }
    public void setClientType(String clientType) { this.clientType = clientType; }

    public TaxReturnStatus getStatus() { return status; }
    public void setStatus(TaxReturnStatus status) { this.status = status; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }

    public LocalDate getDueFrom() { return dueFrom; }
    public void setDueFrom(LocalDate dueFrom) { this.dueFrom = dueFrom; }

    public LocalDate getDueTo() { return dueTo; }
    public void setDueTo(LocalDate dueTo) { this.dueTo = dueTo; }

    public String getQ() { return q; }
    public void setQ(String q) { this.q = q; }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = Math.max(page, 0); }
}
