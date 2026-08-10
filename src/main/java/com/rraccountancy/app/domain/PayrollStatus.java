package com.rraccountancy.app.domain;

public enum PayrollStatus {
    IN_PROGRESS("In Progress"),
    PENDING_APPROVAL("Pending Approval"),
    COMPLETED("Completed"),
    PAID("Paid");

    private final String label;

    PayrollStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
