package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.TaxReturnStatus;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CorporateTaxForm {

    @NotBlank(message = "Client name is required")
    private String clientName;

    @NotBlank(message = "UTR is required")
    @Pattern(regexp = "\\d{10}", message = "UTR must be exactly 10 digits")
    private String utr;

    @NotBlank(message = "Client type is required")
    private String clientType;

    @NotBlank(message = "Industry is required")
    private String industry;

    @NotNull(message = "Accounting period start is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate accountingPeriodStart;

    @NotNull(message = "Accounting period end is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate accountingPeriodEnd;

    @NotNull(message = "Taxable profit is required")
    private BigDecimal taxableProfit;

    @NotNull(message = "Tax payable is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Tax payable can't be negative")
    private BigDecimal taxPayable;

    @NotNull(message = "Status is required")
    private TaxReturnStatus status;

    @NotNull(message = "Due date is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dueDate;

    @NotBlank(message = "Assigned staff member is required")
    private String assignedTo;

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getUtr() { return utr; }
    public void setUtr(String utr) { this.utr = utr; }

    public String getClientType() { return clientType; }
    public void setClientType(String clientType) { this.clientType = clientType; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }

    public LocalDate getAccountingPeriodStart() { return accountingPeriodStart; }
    public void setAccountingPeriodStart(LocalDate accountingPeriodStart) { this.accountingPeriodStart = accountingPeriodStart; }

    public LocalDate getAccountingPeriodEnd() { return accountingPeriodEnd; }
    public void setAccountingPeriodEnd(LocalDate accountingPeriodEnd) { this.accountingPeriodEnd = accountingPeriodEnd; }

    public BigDecimal getTaxableProfit() { return taxableProfit; }
    public void setTaxableProfit(BigDecimal taxableProfit) { this.taxableProfit = taxableProfit; }

    public BigDecimal getTaxPayable() { return taxPayable; }
    public void setTaxPayable(BigDecimal taxPayable) { this.taxPayable = taxPayable; }

    public TaxReturnStatus getStatus() { return status; }
    public void setStatus(TaxReturnStatus status) { this.status = status; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }
}
