package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.TaxReturnStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class VatReturnForm {

    @NotBlank(message = "Client name is required")
    private String clientName;

    @NotBlank(message = "VAT number is required")
    private String vatNumber;

    @NotBlank(message = "Scheme type is required")
    private String schemeType;

    @NotBlank(message = "Client type is required")
    private String clientType;

    @NotNull(message = "VAT period is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate periodStart;

    private BigDecimal salesExVat;

    private BigDecimal purchasesExVat;

    /** Positive = payable, negative = refund, blank = not yet calculated. */
    private BigDecimal vatAmount;

    @NotNull(message = "Status is required")
    private TaxReturnStatus status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate filedOn;

    @NotBlank(message = "Assigned staff member is required")
    private String assignedTo;

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getVatNumber() { return vatNumber; }
    public void setVatNumber(String vatNumber) { this.vatNumber = vatNumber; }

    public String getSchemeType() { return schemeType; }
    public void setSchemeType(String schemeType) { this.schemeType = schemeType; }

    public String getClientType() { return clientType; }
    public void setClientType(String clientType) { this.clientType = clientType; }

    public LocalDate getPeriodStart() { return periodStart; }
    public void setPeriodStart(LocalDate periodStart) { this.periodStart = periodStart; }

    public BigDecimal getSalesExVat() { return salesExVat; }
    public void setSalesExVat(BigDecimal salesExVat) { this.salesExVat = salesExVat; }

    public BigDecimal getPurchasesExVat() { return purchasesExVat; }
    public void setPurchasesExVat(BigDecimal purchasesExVat) { this.purchasesExVat = purchasesExVat; }

    public BigDecimal getVatAmount() { return vatAmount; }
    public void setVatAmount(BigDecimal vatAmount) { this.vatAmount = vatAmount; }

    public TaxReturnStatus getStatus() { return status; }
    public void setStatus(TaxReturnStatus status) { this.status = status; }

    public LocalDate getFiledOn() { return filedOn; }
    public void setFiledOn(LocalDate filedOn) { this.filedOn = filedOn; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }
}
