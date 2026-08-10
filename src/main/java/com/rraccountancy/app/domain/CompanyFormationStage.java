package com.rraccountancy.app.domain;

public enum CompanyFormationStage {
    NAME_RESERVATION("Name Reservation"),
    INCORPORATION("Incorporation"),
    DOCUMENTS("Documents"),
    REVIEW("Review"),
    COMPLETED("Completed");

    private final String label;

    CompanyFormationStage(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
