package com.rraccountancy.app.domain;

public enum TaxReturnStatus {
    /** "Processing" so the CSS pill class doesn't collide with the blue IN_PROGRESS used elsewhere — this one is amber. */
    PROCESSING("In Progress"),
    DOCUMENTS_PENDING("Documents Pending"),
    IN_REVIEW("In Review"),
    FILED("Filed"),
    REFUND_ISSUED("Refund Issued");

    private final String label;

    TaxReturnStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
