package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.TaxReturnStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class InheritanceForm {

    @NotBlank(message = "Client name is required")
    private String clientName;

    @NotBlank(message = "UTR is required")
    private String utr;

    @NotBlank(message = "Client type is required")
    private String clientType;

    @NotBlank(message = "Planning / report type is required")
    private String planningType;

    @NotNull(message = "Tax year is required")
    private Integer taxYearStart;

    @NotNull(message = "Estate value is required")
    private BigDecimal estateValue;

    @NotNull(message = "Nil rate band available is required")
    private BigDecimal nilRateBandAvailable;

    @NotNull(message = "Estimated tax liability is required")
    private BigDecimal taxLiability;

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

    public String getPlanningType() { return planningType; }
    public void setPlanningType(String planningType) { this.planningType = planningType; }

    public Integer getTaxYearStart() { return taxYearStart; }
    public void setTaxYearStart(Integer taxYearStart) { this.taxYearStart = taxYearStart; }

    public BigDecimal getEstateValue() { return estateValue; }
    public void setEstateValue(BigDecimal estateValue) { this.estateValue = estateValue; }

    public BigDecimal getNilRateBandAvailable() { return nilRateBandAvailable; }
    public void setNilRateBandAvailable(BigDecimal nilRateBandAvailable) { this.nilRateBandAvailable = nilRateBandAvailable; }

    public BigDecimal getTaxLiability() { return taxLiability; }
    public void setTaxLiability(BigDecimal taxLiability) { this.taxLiability = taxLiability; }

    public TaxReturnStatus getStatus() { return status; }
    public void setStatus(TaxReturnStatus status) { this.status = status; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }
}
