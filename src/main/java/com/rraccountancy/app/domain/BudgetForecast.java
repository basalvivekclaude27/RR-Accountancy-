package com.rraccountancy.app.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Entity
@Table(name = "budget_forecasts")
public class BudgetForecast {

    private static final DateTimeFormatter UPDATED_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_name", nullable = false, length = 150)
    private String clientName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private BudgetType type;

    @Column(name = "financial_year_start", nullable = false)
    private Integer financialYearStart;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private BudgetPeriod period;

    @Column(name = "budgeted_amount", nullable = false, precision = 14, scale = 2)
    private BigDecimal budgetedAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private BudgetStatus status;

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

    public String getFinancialYearLabel() {
        return financialYearStart == null ? "" : financialYearStart + " - " + (financialYearStart + 1);
    }

    public String getBudgetedAmountLabel() {
        return budgetedAmount == null ? "" : "£" + String.format(Locale.UK, "%,.0f", budgetedAmount);
    }

    public String getLastUpdatedLabel() {
        return lastUpdated == null ? "" : lastUpdated.format(UPDATED_FORMAT);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public LocalDate getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDate lastUpdated) { this.lastUpdated = lastUpdated; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
