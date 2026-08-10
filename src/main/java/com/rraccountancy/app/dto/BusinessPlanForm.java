package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.BusinessPlanStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class BusinessPlanForm {

    @NotBlank(message = "Client name is required")
    private String clientName;

    /** Optional — BusinessPlanService auto-generates one (e.g. BP-1053) when left blank. */
    private String planRef;

    @NotBlank(message = "Plan name is required")
    private String planName;

    @NotBlank(message = "Plan type is required")
    private String planType;

    @NotBlank(message = "Industry is required")
    private String industry;

    @NotNull(message = "Status is required")
    private BusinessPlanStatus status;

    @NotNull(message = "Revenue potential is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Amount can't be negative")
    private BigDecimal revenuePotential;

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getPlanRef() { return planRef; }
    public void setPlanRef(String planRef) { this.planRef = planRef; }

    public String getPlanName() { return planName; }
    public void setPlanName(String planName) { this.planName = planName; }

    public String getPlanType() { return planType; }
    public void setPlanType(String planType) { this.planType = planType; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }

    public BusinessPlanStatus getStatus() { return status; }
    public void setStatus(BusinessPlanStatus status) { this.status = status; }

    public BigDecimal getRevenuePotential() { return revenuePotential; }
    public void setRevenuePotential(BigDecimal revenuePotential) { this.revenuePotential = revenuePotential; }
}
