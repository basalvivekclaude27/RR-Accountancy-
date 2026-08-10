package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.TaxReturnStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CapitalGainForm {

    @NotBlank(message = "Client name is required")
    private String clientName;

    @NotBlank(message = "UTR is required")
    private String utr;

    @NotBlank(message = "Client type is required")
    private String clientType;

    @NotBlank(message = "Asset type is required")
    private String assetType;

    @NotNull(message = "Tax year is required")
    private Integer taxYearStart;

    @NotNull(message = "Date of disposal is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfDisposal;

    @NotNull(message = "Gain or loss amount is required")
    private BigDecimal gainOrLoss;

    @NotNull(message = "Tax payable is required")
    private BigDecimal taxPayable;

    @NotNull(message = "Status is required")
    private TaxReturnStatus status;

    @NotBlank(message = "Assigned staff member is required")
    private String assignedTo;

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getUtr() { return utr; }
    public void setUtr(String utr) { this.utr = utr; }

    public String getClientType() { return clientType; }
    public void setClientType(String clientType) { this.clientType = clientType; }

    public String getAssetType() { return assetType; }
    public void setAssetType(String assetType) { this.assetType = assetType; }

    public Integer getTaxYearStart() { return taxYearStart; }
    public void setTaxYearStart(Integer taxYearStart) { this.taxYearStart = taxYearStart; }

    public LocalDate getDateOfDisposal() { return dateOfDisposal; }
    public void setDateOfDisposal(LocalDate dateOfDisposal) { this.dateOfDisposal = dateOfDisposal; }

    public BigDecimal getGainOrLoss() { return gainOrLoss; }
    public void setGainOrLoss(BigDecimal gainOrLoss) { this.gainOrLoss = gainOrLoss; }

    public BigDecimal getTaxPayable() { return taxPayable; }
    public void setTaxPayable(BigDecimal taxPayable) { this.taxPayable = taxPayable; }

    public TaxReturnStatus getStatus() { return status; }
    public void setStatus(TaxReturnStatus status) { this.status = status; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }
}
