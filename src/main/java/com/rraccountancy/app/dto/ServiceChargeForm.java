package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.ServiceChargeStatus;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.YearMonth;

public class ServiceChargeForm {

    @NotBlank(message = "Property / development name is required")
    private String propertyName;

    /** Optional — ServiceChargeAccountService auto-generates one (e.g. PR-1043) when left blank. */
    private String propertyRef;

    @NotNull(message = "Unit count is required")
    @Min(value = 1, message = "Must have at least 1 unit")
    private Integer units;

    @NotNull(message = "Financial year is required")
    @Min(value = 2000, message = "Enter a valid year")
    @Max(value = 2100, message = "Enter a valid year")
    private Integer financialYearStart;

    @NotNull(message = "Period is required")
    @DateTimeFormat(pattern = "yyyy-MM")
    private YearMonth period;

    @NotNull(message = "Budgeted amount is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Amount can't be negative")
    private BigDecimal budgetedAmount;

    @NotNull(message = "Collected amount is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Amount can't be negative")
    private BigDecimal collectedAmount;

    @NotNull(message = "Status is required")
    private ServiceChargeStatus status;

    public String getPropertyName() { return propertyName; }
    public void setPropertyName(String propertyName) { this.propertyName = propertyName; }

    public String getPropertyRef() { return propertyRef; }
    public void setPropertyRef(String propertyRef) { this.propertyRef = propertyRef; }

    public Integer getUnits() { return units; }
    public void setUnits(Integer units) { this.units = units; }

    public Integer getFinancialYearStart() { return financialYearStart; }
    public void setFinancialYearStart(Integer financialYearStart) { this.financialYearStart = financialYearStart; }

    public YearMonth getPeriod() { return period; }
    public void setPeriod(YearMonth period) { this.period = period; }

    public BigDecimal getBudgetedAmount() { return budgetedAmount; }
    public void setBudgetedAmount(BigDecimal budgetedAmount) { this.budgetedAmount = budgetedAmount; }

    public BigDecimal getCollectedAmount() { return collectedAmount; }
    public void setCollectedAmount(BigDecimal collectedAmount) { this.collectedAmount = collectedAmount; }

    public ServiceChargeStatus getStatus() { return status; }
    public void setStatus(ServiceChargeStatus status) { this.status = status; }
}
