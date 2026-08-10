package com.rraccountancy.app.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Entity
@Table(name = "financial_reports")
public class FinancialReport {

    private static final DateTimeFormatter PERIOD_FORMAT = DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH);
    private static final DateTimeFormatter GENERATED_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_name", nullable = false, length = 150)
    private String clientName;

    @Enumerated(EnumType.STRING)
    @Column(name = "report_type", nullable = false, length = 30)
    private ReportType reportType;

    @Column(name = "period_date", nullable = false)
    private LocalDate periodDate;

    /** First year of the financial year this report falls in, e.g. 2024 for "2024 - 2025". */
    @Column(name = "financial_year_start", nullable = false)
    private Integer financialYearStart;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private AccountStatus status;

    @Column(name = "generated_on", nullable = false)
    private LocalDate generatedOn;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        if (this.generatedOn == null) {
            this.generatedOn = now.toLocalDate();
        }
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public String getInitials() {
        if (clientName == null || clientName.isBlank()) {
            return "?";
        }
        StringBuilder sb = new StringBuilder();
        for (String word : clientName.trim().split("\\s+")) {
            if (sb.length() >= 3) break;
            if (!word.isEmpty() && Character.isLetterOrDigit(word.charAt(0))) {
                sb.append(Character.toUpperCase(word.charAt(0)));
            }
        }
        return sb.length() > 0 ? sb.toString() : clientName.substring(0, 1).toUpperCase(Locale.ROOT);
    }

    public String getPeriodLabel() {
        return periodDate == null ? "" : periodDate.format(PERIOD_FORMAT);
    }

    public String getFinancialYearLabel() {
        return financialYearStart == null ? "" : financialYearStart + " - " + (financialYearStart + 1);
    }

    public String getGeneratedOnLabel() {
        return generatedOn == null ? "" : generatedOn.format(GENERATED_FORMAT);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public ReportType getReportType() { return reportType; }
    public void setReportType(ReportType reportType) { this.reportType = reportType; }

    public LocalDate getPeriodDate() { return periodDate; }
    public void setPeriodDate(LocalDate periodDate) { this.periodDate = periodDate; }

    public Integer getFinancialYearStart() { return financialYearStart; }
    public void setFinancialYearStart(Integer financialYearStart) { this.financialYearStart = financialYearStart; }

    public AccountStatus getStatus() { return status; }
    public void setStatus(AccountStatus status) { this.status = status; }

    public LocalDate getGeneratedOn() { return generatedOn; }
    public void setGeneratedOn(LocalDate generatedOn) { this.generatedOn = generatedOn; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
