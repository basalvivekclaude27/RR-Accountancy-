package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.AccountStatus;
import com.rraccountancy.app.domain.ReportType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.YearMonth;

public class FinancialReportForm {

    @NotBlank(message = "Client / business name is required")
    private String clientName;

    @NotNull(message = "Report type is required")
    private ReportType reportType;

    @NotNull(message = "Period is required")
    @DateTimeFormat(pattern = "yyyy-MM")
    private YearMonth period;

    @NotNull(message = "Financial year is required")
    @Min(value = 2000, message = "Enter a valid year")
    @Max(value = 2100, message = "Enter a valid year")
    private Integer financialYearStart;

    @NotNull(message = "Status is required")
    private AccountStatus status;

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public ReportType getReportType() { return reportType; }
    public void setReportType(ReportType reportType) { this.reportType = reportType; }

    public YearMonth getPeriod() { return period; }
    public void setPeriod(YearMonth period) { this.period = period; }

    public Integer getFinancialYearStart() { return financialYearStart; }
    public void setFinancialYearStart(Integer financialYearStart) { this.financialYearStart = financialYearStart; }

    public AccountStatus getStatus() { return status; }
    public void setStatus(AccountStatus status) { this.status = status; }
}
