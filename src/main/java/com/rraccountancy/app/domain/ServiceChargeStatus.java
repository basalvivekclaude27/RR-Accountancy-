package com.rraccountancy.app.domain;

public enum ServiceChargeStatus {
    ON_TRACK("On Track"),
    ATTENTION("Attention"),
    BEHIND("Behind");

    private final String label;

    ServiceChargeStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
