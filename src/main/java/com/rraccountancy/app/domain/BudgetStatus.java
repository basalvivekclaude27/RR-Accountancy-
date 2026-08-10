package com.rraccountancy.app.domain;

public enum BudgetStatus {
    IN_PROGRESS("In Progress"),
    PENDING_REVIEW("Pending Review"),
    APPROVED("Approved"),
    COMPLETED("Completed");

    private final String label;

    BudgetStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
