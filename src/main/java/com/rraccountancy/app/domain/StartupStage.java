package com.rraccountancy.app.domain;

public enum StartupStage {
    BUSINESS_PLAN("Business Plan"),
    COMPANY_FORMATION("Company Formation"),
    REGISTRATION("Registration"),
    DOCUMENT_SETUP("Document Setup"),
    LAUNCHED("Launched");

    private final String label;

    StartupStage(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
