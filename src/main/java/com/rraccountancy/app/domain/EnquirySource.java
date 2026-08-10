package com.rraccountancy.app.domain;

public enum EnquirySource {
    WEBSITE("Website"),
    REFERRAL("Referral"),
    ADVERTISEMENT("Advertisement"),
    SOCIAL_MEDIA("Social Media");

    private final String label;

    EnquirySource(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
