package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.BudgetPeriod;
import com.rraccountancy.app.domain.BudgetStatus;
import com.rraccountancy.app.domain.BudgetType;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class BudgetForecastForm {

    @NotBlank(message = "Client / business name is required")
    private String clientName;

    @NotNull(message = "Type is required")
    private BudgetType type;

    @NotNull(message = "Financial year is required")
    @Min(value = 2000, message = "Enter a valid year")
    @Max(value = 2100, message = "Enter a valid year")
    private Integer financialYearStart;

    @NotNull(message = "Period is required")
    private BudgetPeriod period;

    @NotNull(message = "Budgeted amount is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Amount can't be negative")
    private BigDecimal budgetedAmount;

    @NotNull(message = "Status is required")
    private BudgetStatus status;

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public BudgetType getType() { return type; }
    public void setType(BudgetType type) { this.type = type; }

    public Integer getFinancialYearStart() { return financialYearStart; }
    public void setFinancialYearStart(Integer financialYearStart) { this.financialYearStart = financialYearStart; }

    public BudgetPeriod getPeriod() { return period; }
    public void setPeriod(BudgetPeriod period) { this.period = period; }

    public BigDecimal getBudgetedAmount() { return budgetedAmount; }
    public void setBudgetedAmount(BigDecimal budgetedAmount) { this.budgetedAmount = budgetedAmount; }

    public BudgetStatus getStatus() { return status; }
    public void setStatus(BudgetStatus status) { this.status = status; }
}
