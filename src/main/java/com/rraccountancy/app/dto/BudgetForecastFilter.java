package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.BudgetPeriod;
import com.rraccountancy.app.domain.BudgetStatus;
import com.rraccountancy.app.domain.BudgetType;

/** Query-string-bound filter state for the Budgets &amp; Forecasts list. */
public class BudgetForecastFilter {

    private String client;
    private BudgetType type;
    private Integer financialYear;
    private BudgetPeriod period;
    private BudgetStatus status;
    private String q;
    private int page = 0;

    public boolean hasClient() { return client != null && !client.isBlank(); }
    public boolean hasQuery() { return q != null && !q.isBlank(); }

    public String getClient() { return client; }
    public void setClient(String client) { this.client = client; }

    public BudgetType getType() { return type; }
    public void setType(BudgetType type) { this.type = type; }

    public Integer getFinancialYear() { return financialYear; }
    public void setFinancialYear(Integer financialYear) { this.financialYear = financialYear; }

    public BudgetPeriod getPeriod() { return period; }
    public void setPeriod(BudgetPeriod period) { this.period = period; }

    public BudgetStatus getStatus() { return status; }
    public void setStatus(BudgetStatus status) { this.status = status; }

    public String getQ() { return q; }
    public void setQ(String q) { this.q = q; }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = Math.max(page, 0); }
}
