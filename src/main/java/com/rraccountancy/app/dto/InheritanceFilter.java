package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.TaxReturnStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/** Query-string-bound filter state for the IHT Cases Overview list. */
public class InheritanceFilter {

    private Integer taxYear;
    private String clientType;
    private TaxReturnStatus status;
    private String planningType;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateFrom;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateTo;

    private String q;
    private int page = 0;

    public boolean hasClientType() { return clientType != null && !clientType.isBlank(); }
    public boolean hasPlanningType() { return planningType != null && !planningType.isBlank(); }
    public boolean hasQuery() { return q != null && !q.isBlank(); }

    public Integer getTaxYear() { return taxYear; }
    public void setTaxYear(Integer taxYear) { this.taxYear = taxYear; }

    public String getClientType() { return clientType; }
    public void setClientType(String clientType) { this.clientType = clientType; }

    public TaxReturnStatus getStatus() { return status; }
    public void setStatus(TaxReturnStatus status) { this.status = status; }

    public String getPlanningType() { return planningType; }
    public void setPlanningType(String planningType) { this.planningType = planningType; }

    public LocalDate getDateFrom() { return dateFrom; }
    public void setDateFrom(LocalDate dateFrom) { this.dateFrom = dateFrom; }

    public LocalDate getDateTo() { return dateTo; }
    public void setDateTo(LocalDate dateTo) { this.dateTo = dateTo; }

    public String getQ() { return q; }
    public void setQ(String q) { this.q = q; }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = Math.max(page, 0); }
}
