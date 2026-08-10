package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.AccountStatus;
import com.rraccountancy.app.domain.AccountType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AccountsPreparationJobForm {

    @NotBlank(message = "Client / business name is required")
    private String clientName;

    /** Optional — AccountsPreparationJobService auto-generates one (e.g. ACC-1043) when left blank. */
    private String jobRef;

    @NotNull(message = "Financial year is required")
    @Min(value = 2000, message = "Enter a valid year")
    @Max(value = 2100, message = "Enter a valid year")
    private Integer financialYearStart;

    @NotNull(message = "Account type is required")
    private AccountType accountType;

    @NotNull(message = "Status is required")
    private AccountStatus status;

    @NotBlank(message = "Assigned staff member is required")
    private String assignedTo;

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getJobRef() { return jobRef; }
    public void setJobRef(String jobRef) { this.jobRef = jobRef; }

    public Integer getFinancialYearStart() { return financialYearStart; }
    public void setFinancialYearStart(Integer financialYearStart) { this.financialYearStart = financialYearStart; }

    public AccountType getAccountType() { return accountType; }
    public void setAccountType(AccountType accountType) { this.accountType = accountType; }

    public AccountStatus getStatus() { return status; }
    public void setStatus(AccountStatus status) { this.status = status; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }
}
