package com.rraccountancy.app.domain;

public enum BudgetType {
    ANNUAL_BUDGET("Annual Budget"),
    FORECAST("Forecast"),
    BUDGET_REVISION("Budget Revision");

    private final String label;

    BudgetType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
