package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.AccountStatus;
import com.rraccountancy.app.domain.AccountType;

/** Query-string-bound filter state for the Accounts Preparation Jobs list. */
public class AccountsPreparationJobFilter {

    private String client;
    private AccountStatus status;
    private Integer financialYear;
    private AccountType accountType;
    private String assignedTo;
    private String q;
    private int page = 0;

    public boolean hasClient() { return client != null && !client.isBlank(); }
    public boolean hasAssignedTo() { return assignedTo != null && !assignedTo.isBlank(); }
    public boolean hasQuery() { return q != null && !q.isBlank(); }

    public String getClient() { return client; }
    public void setClient(String client) { this.client = client; }

    public AccountStatus getStatus() { return status; }
    public void setStatus(AccountStatus status) { this.status = status; }

    public Integer getFinancialYear() { return financialYear; }
    public void setFinancialYear(Integer financialYear) { this.financialYear = financialYear; }

    public AccountType getAccountType() { return accountType; }
    public void setAccountType(AccountType accountType) { this.accountType = accountType; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    public String getQ() { return q; }
    public void setQ(String q) { this.q = q; }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = Math.max(page, 0); }
}
