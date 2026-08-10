package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.TaxReturnStatus;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class BusinessTaxReturnForm {

    @NotBlank(message = "Client name is required")
    private String clientName;

    @NotBlank(message = "UTR is required")
    @Pattern(regexp = "\\d{10}", message = "UTR must be exactly 10 digits")
    private String utr;

    @NotBlank(message = "Business type is required")
    private String businessType;

    @NotBlank(message = "Industry is required")
    private String industry;

    @NotNull(message = "Tax year is required")
    @Min(value = 2000, message = "Enter a valid year")
    @Max(value = 2100, message = "Enter a valid year")
    private Integer taxYearStart;

    @NotNull(message = "Profit before tax is required")
    private BigDecimal profitBeforeTax;

    @NotNull(message = "Tax payable is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Tax payable can't be negative")
    private BigDecimal taxPayable;

    @NotNull(message = "Status is required")
    private TaxReturnStatus status;

    @NotBlank(message = "Assigned staff member is required")
    private String assignedTo;

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getUtr() { return utr; }
    public void setUtr(String utr) { this.utr = utr; }

    public String getBusinessType() { return businessType; }
    public void setBusinessType(String businessType) { this.businessType = businessType; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }

    public Integer getTaxYearStart() { return taxYearStart; }
    public void setTaxYearStart(Integer taxYearStart) { this.taxYearStart = taxYearStart; }

    public BigDecimal getProfitBeforeTax() { return profitBeforeTax; }
    public void setProfitBeforeTax(BigDecimal profitBeforeTax) { this.profitBeforeTax = profitBeforeTax; }

    public BigDecimal getTaxPayable() { return taxPayable; }
    public void setTaxPayable(BigDecimal taxPayable) { this.taxPayable = taxPayable; }

    public TaxReturnStatus getStatus() { return status; }
    public void setStatus(TaxReturnStatus status) { this.status = status; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }
}
