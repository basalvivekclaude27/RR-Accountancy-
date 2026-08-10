package com.rraccountancy.app.domain;

public enum JobStatus {
    IN_PROGRESS("In Progress"),
    PENDING_REVIEW("Pending Review"),
    COMPLETED("Completed"),
    PENDING_INFO("Pending Info");

    private final String label;

    JobStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
