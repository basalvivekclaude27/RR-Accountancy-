package com.rraccountancy.app.domain;

public enum AccountStatus {
    IN_PROGRESS("In Progress"),
    PENDING_REVIEW("Pending Review"),
    DRAFT("Draft"),
    COMPLETED("Completed");

    private final String label;

    AccountStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
