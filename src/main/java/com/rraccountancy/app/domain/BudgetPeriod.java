package com.rraccountancy.app.domain;

public enum BudgetPeriod {
    YEARLY("Yearly"),
    Q1("Q1 (Apr - Jun)"),
    Q2("Q2 (Jul - Sep)"),
    Q3("Q3 (Oct - Dec)"),
    Q4("Q4 (Jan - Mar)");

    private final String label;

    BudgetPeriod(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
