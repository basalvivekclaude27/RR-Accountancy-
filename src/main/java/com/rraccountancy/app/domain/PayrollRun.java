package com.rraccountancy.app.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Entity
@Table(name = "payroll_runs")
public class PayrollRun {

    private static final DateTimeFormatter MONTH_FORMAT = DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH);
    private static final DateTimeFormatter UPDATED_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_name", nullable = false, length = 150)
    private String clientName;

    @Column(name = "payroll_month", nullable = false)
    private LocalDate payrollMonth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PayrollFrequency frequency;

    @Column(nullable = false)
    private Integer employees;

    @Column(name = "net_pay", nullable = false, precision = 12, scale = 2)
    private BigDecimal netPay;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PayrollStatus status;

    @Column(name = "assigned_to", nullable = false, length = 100)
    private String assignedTo;

    @Column(name = "last_updated", nullable = false)
    private LocalDate lastUpdated;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        if (this.lastUpdated == null) {
            this.lastUpdated = now.toLocalDate();
        }
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now();
        this.lastUpdated = LocalDate.now();
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

    public String getPayrollMonthLabel() {
        return payrollMonth == null ? "" : payrollMonth.format(MONTH_FORMAT);
    }

    public String getNetPayLabel() {
        return netPay == null ? "" : "£" + String.format(Locale.UK, "%,.0f", netPay);
    }

    public String getLastUpdatedLabel() {
        return lastUpdated == null ? "" : lastUpdated.format(UPDATED_FORMAT);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public LocalDate getPayrollMonth() { return payrollMonth; }
    public void setPayrollMonth(LocalDate payrollMonth) { this.payrollMonth = payrollMonth; }

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

    public LocalDate getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDate lastUpdated) { this.lastUpdated = lastUpdated; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
