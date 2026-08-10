package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.TaxReturnStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public class TaxReturnForm {

    @NotBlank(message = "Client name is required")
    private String clientName;

    @NotBlank(message = "UTR is required")
    @Pattern(regexp = "\\d{10}", message = "UTR must be exactly 10 digits")
    private String utr;

    @NotBlank(message = "Client type is required")
    private String clientType;

    @NotNull(message = "Tax year is required")
    @Min(value = 2000, message = "Enter a valid year")
    @Max(value = 2100, message = "Enter a valid year")
    private Integer taxYearStart;

    @NotNull(message = "Status is required")
    private TaxReturnStatus status;

    /** Positive = refund, negative = payable, blank = not yet known. */
    private BigDecimal refundAmount;

    @NotBlank(message = "Assigned staff member is required")
    private String assignedTo;

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getUtr() { return utr; }
    public void setUtr(String utr) { this.utr = utr; }

    public String getClientType() { return clientType; }
    public void setClientType(String clientType) { this.clientType = clientType; }

    public Integer getTaxYearStart() { return taxYearStart; }
    public void setTaxYearStart(Integer taxYearStart) { this.taxYearStart = taxYearStart; }

    public TaxReturnStatus getStatus() { return status; }
    public void setStatus(TaxReturnStatus status) { this.status = status; }

    public BigDecimal getRefundAmount() { return refundAmount; }
    public void setRefundAmount(BigDecimal refundAmount) { this.refundAmount = refundAmount; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }
}
