package com.rraccountancy.app.domain;

public enum CompanyFormationStatus {
    IN_PROGRESS("In Progress"),
    PENDING("Pending"),
    COMPLETED("Completed");

    private final String label;

    CompanyFormationStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
