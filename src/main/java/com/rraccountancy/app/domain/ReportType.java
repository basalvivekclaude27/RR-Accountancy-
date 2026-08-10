package com.rraccountancy.app.domain;

public enum ReportType {
    MANAGEMENT_ACCOUNTS("Management Accounts"),
    FINANCIAL_STATEMENTS("Financial Statements"),
    CASH_FLOW_STATEMENT("Cash Flow Statement"),
    PROFIT_LOSS_STATEMENT("Profit & Loss Statement"),
    BALANCE_SHEET("Balance Sheet");

    private final String label;

    ReportType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
