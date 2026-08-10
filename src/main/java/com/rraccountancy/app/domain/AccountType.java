package com.rraccountancy.app.domain;

public enum AccountType {
    STATUTORY_ACCOUNTS("Statutory Accounts"),
    MANAGEMENT_ACCOUNTS("Management Accounts");

    private final String label;

    AccountType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
