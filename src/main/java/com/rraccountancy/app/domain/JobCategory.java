package com.rraccountancy.app.domain;

public enum JobCategory {
    BOOKKEEPING("Bookkeeping"),
    MANAGEMENT_ACCOUNTS("Management Accounts"),
    VAT_RETURNS("VAT Returns"),
    PAYROLL("Payroll");

    private final String label;

    JobCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
