package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.TaxReturnStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/** Query-string-bound filter state for the VAT Returns Overview list. */
public class VatReturnFilter {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate period;

    private String clientType;
    private TaxReturnStatus status;
    private String schemeType;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateFrom;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateTo;

    private String q;
    private int page = 0;

    public boolean hasClientType() { return clientType != null && !clientType.isBlank(); }
    public boolean hasSchemeType() { return schemeType != null && !schemeType.isBlank(); }
    public boolean hasQuery() { return q != null && !q.isBlank(); }

    public LocalDate getPeriod() { return period; }
    public void setPeriod(LocalDate period) { this.period = period; }

    public String getClientType() { return clientType; }
    public void setClientType(String clientType) { this.clientType = clientType; }

    public TaxReturnStatus getStatus() { return status; }
    public void setStatus(TaxReturnStatus status) { this.status = status; }

    public String getSchemeType() { return schemeType; }
    public void setSchemeType(String schemeType) { this.schemeType = schemeType; }

    public LocalDate getDateFrom() { return dateFrom; }
    public void setDateFrom(LocalDate dateFrom) { this.dateFrom = dateFrom; }

    public LocalDate getDateTo() { return dateTo; }
    public void setDateTo(LocalDate dateTo) { this.dateTo = dateTo; }

    public String getQ() { return q; }
    public void setQ(String q) { this.q = q; }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = Math.max(page, 0); }
}
