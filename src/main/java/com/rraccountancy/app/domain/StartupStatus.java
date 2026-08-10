package com.rraccountancy.app.domain;

public enum StartupStatus {
    NEW("New"),
    IN_PROGRESS("In Progress"),
    AWAITING_INFO("Pending Info"),
    COMPLETED("Completed");

    private final String label;

    StartupStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
