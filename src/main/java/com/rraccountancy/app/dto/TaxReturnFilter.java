package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.TaxReturnStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/** Query-string-bound filter state for the Tax Returns Overview list. */
public class TaxReturnFilter {

    private Integer taxYear;
    private String clientType;
    private TaxReturnStatus status;
    private String assignedTo;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateFrom;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateTo;

    private String q;
    private int page = 0;

    public boolean hasClientType() { return clientType != null && !clientType.isBlank(); }
    public boolean hasAssignedTo() { return assignedTo != null && !assignedTo.isBlank(); }
    public boolean hasQuery() { return q != null && !q.isBlank(); }

    public Integer getTaxYear() { return taxYear; }
    public void setTaxYear(Integer taxYear) { this.taxYear = taxYear; }

    public String getClientType() { return clientType; }
    public void setClientType(String clientType) { this.clientType = clientType; }

    public TaxReturnStatus getStatus() { return status; }
    public void setStatus(TaxReturnStatus status) { this.status = status; }

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
