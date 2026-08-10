package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.PayrollFrequency;
import com.rraccountancy.app.domain.PayrollStatus;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.YearMonth;

public class PayrollRunForm {

    @NotBlank(message = "Client / business name is required")
    private String clientName;

    @NotNull(message = "Payroll month is required")
    @DateTimeFormat(pattern = "yyyy-MM")
    private YearMonth payrollMonth;

    @NotNull(message = "Frequency is required")
    private PayrollFrequency frequency;

    @NotNull(message = "Employee count is required")
    @Min(value = 1, message = "Must have at least 1 employee")
    private Integer employees;

    @NotNull(message = "Net pay is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Net pay can't be negative")
    private BigDecimal netPay;

    @NotNull(message = "Status is required")
    private PayrollStatus status;

    @NotBlank(message = "Assigned staff member is required")
    private String assignedTo;

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public YearMonth getPayrollMonth() { return payrollMonth; }
    public void setPayrollMonth(YearMonth payrollMonth) { this.payrollMonth = payrollMonth; }

    public PayrollFrequency getFrequency() { return frequency; }
    public void setFrequency(PayrollFrequency frequency) { this.frequency = frequency; }

    public Integer getEmployees() { return employees; }
    public void setEmployees(Integer employees) { this.employees = employees; }

    public BigDecimal getNetPay() { return netPay; }
    public void setNetPay(BigDecimal netPay) { this.netPay = netPay; }

    public PayrollStatus getStatus() { return status; }
    public void setStatus(PayrollStatus status) { this.status = status; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }
}
