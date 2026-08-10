package com.rraccountancy.app.domain;

public enum PayrollFrequency {
    WEEKLY("Weekly"),
    FORTNIGHTLY("Fortnightly"),
    FOUR_WEEKLY("Four-Weekly"),
    MONTHLY("Monthly");

    private final String label;

    PayrollFrequency(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
