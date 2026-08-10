package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.AccountStatus;
import com.rraccountancy.app.domain.ReportType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.YearMonth;

/** Query-string-bound filter state for the Financial &amp; Management Reports list. */
public class FinancialReportFilter {

    private String client;
    private Integer financialYear;
    private ReportType reportType;

    @DateTimeFormat(pattern = "yyyy-MM")
    private YearMonth period;

    private AccountStatus status;
    private String q;
    private int page = 0;

    public boolean hasClient() { return client != null && !client.isBlank(); }
    public boolean hasQuery() { return q != null && !q.isBlank(); }

    public String getClient() { return client; }
    public void setClient(String client) { this.client = client; }

    public Integer getFinancialYear() { return financialYear; }
    public void setFinancialYear(Integer financialYear) { this.financialYear = financialYear; }

    public ReportType getReportType() { return reportType; }
    public void setReportType(ReportType reportType) { this.reportType = reportType; }

    public YearMonth getPeriod() { return period; }
    public void setPeriod(YearMonth period) { this.period = period; }

    public AccountStatus getStatus() { return status; }
    public void setStatus(AccountStatus status) { this.status = status; }

    public String getQ() { return q; }
    public void setQ(String q) { this.q = q; }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = Math.max(page, 0); }
}
