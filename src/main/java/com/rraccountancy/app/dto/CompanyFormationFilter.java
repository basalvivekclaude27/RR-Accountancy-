package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.CompanyFormationStatus;
import com.rraccountancy.app.domain.EnquirySource;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/** Query-string-bound filter state for the Company Formation Progress list. */
public class CompanyFormationFilter {

    private EnquirySource source;
    private String companyType;
    private CompanyFormationStatus status;
    private String jurisdiction;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateFrom;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateTo;

    private String q;
    private int page = 0;

    public boolean hasCompanyType() { return companyType != null && !companyType.isBlank(); }
    public boolean hasJurisdiction() { return jurisdiction != null && !jurisdiction.isBlank(); }
    public boolean hasQuery() { return q != null && !q.isBlank(); }

    public EnquirySource getSource() { return source; }
    public void setSource(EnquirySource source) { this.source = source; }

    public String getCompanyType() { return companyType; }
    public void setCompanyType(String companyType) { this.companyType = companyType; }

    public CompanyFormationStatus getStatus() { return status; }
    public void setStatus(CompanyFormationStatus status) { this.status = status; }

    public String getJurisdiction() { return jurisdiction; }
    public void setJurisdiction(String jurisdiction) { this.jurisdiction = jurisdiction; }

    public LocalDate getDateFrom() { return dateFrom; }
    public void setDateFrom(LocalDate dateFrom) { this.dateFrom = dateFrom; }

    public LocalDate getDateTo() { return dateTo; }
    public void setDateTo(LocalDate dateTo) { this.dateTo = dateTo; }

    public String getQ() { return q; }
    public void setQ(String q) { this.q = q; }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = Math.max(page, 0); }
}
