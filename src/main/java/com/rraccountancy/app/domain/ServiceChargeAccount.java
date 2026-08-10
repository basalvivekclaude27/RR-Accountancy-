package com.rraccountancy.app.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Entity
@Table(name = "service_charge_accounts")
public class ServiceChargeAccount {

    private static final DateTimeFormatter PERIOD_FORMAT = DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "property_name", nullable = false, length = 150)
    private String propertyName;

    @Column(name = "property_ref", nullable = false, length = 30)
    private String propertyRef;

    @Column(nullable = false)
    private Integer units;

    @Column(name = "financial_year_start", nullable = false)
    private Integer financialYearStart;

    @Column(name = "period_date", nullable = false)
    private LocalDate periodDate;

    @Column(name = "budgeted_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal budgetedAmount;

    @Column(name = "collected_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal collectedAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ServiceChargeStatus status;

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

    public String getFinancialYearLabel() {
        return financialYearStart == null ? "" : financialYearStart + " - " + (financialYearStart + 1);
    }

    public String getPeriodLabel() {
        return periodDate == null ? "" : periodDate.format(PERIOD_FORMAT);
    }

    public String getBudgetedAmountLabel() {
        return budgetedAmount == null ? "" : "£" + String.format(Locale.UK, "%,.0f", budgetedAmount);
    }

    public String getCollectedAmountLabel() {
        return collectedAmount == null ? "" : "£" + String.format(Locale.UK, "%,.0f", collectedAmount);
    }

    /** Collection percentage 0-100, rounded to nearest whole number. */
    public int getCollectionPercent() {
        if (budgetedAmount == null || collectedAmount == null || budgetedAmount.signum() == 0) {
            return 0;
        }
        return collectedAmount.multiply(BigDecimal.valueOf(100))
                .divide(budgetedAmount, 0, RoundingMode.HALF_UP)
                .intValue();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPropertyName() { return propertyName; }
    public void setPropertyName(String propertyName) { this.propertyName = propertyName; }

    public String getPropertyRef() { return propertyRef; }
    public void setPropertyRef(String propertyRef) { this.propertyRef = propertyRef; }

    public Integer getUnits() { return units; }
    public void setUnits(Integer units) { this.units = units; }

    public Integer getFinancialYearStart() { return financialYearStart; }
    public void setFinancialYearStart(Integer financialYearStart) { this.financialYearStart = financialYearStart; }

    public LocalDate getPeriodDate() { return periodDate; }
    public void setPeriodDate(LocalDate periodDate) { this.periodDate = periodDate; }

    public BigDecimal getBudgetedAmount() { return budgetedAmount; }
    public void setBudgetedAmount(BigDecimal budgetedAmount) { this.budgetedAmount = budgetedAmount; }

    public BigDecimal getCollectedAmount() { return collectedAmount; }
    public void setCollectedAmount(BigDecimal collectedAmount) { this.collectedAmount = collectedAmount; }

    public ServiceChargeStatus getStatus() { return status; }
    public void setStatus(ServiceChargeStatus status) { this.status = status; }

    public LocalDate getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDate lastUpdated) { this.lastUpdated = lastUpdated; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
