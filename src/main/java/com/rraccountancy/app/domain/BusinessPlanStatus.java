package com.rraccountancy.app.domain;

public enum BusinessPlanStatus {
    NOT_STARTED("Not Started"),
    IN_PROGRESS("In Progress"),
    PENDING_REVIEW("Pending Review"),
    ON_HOLD("On Hold"),
    COMPLETED("Completed");

    private final String label;

    BusinessPlanStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
