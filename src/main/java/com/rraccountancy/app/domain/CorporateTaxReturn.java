package com.rraccountancy.app.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Entity
@Table(name = "corporate_tax_returns")
public class CorporateTaxReturn {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
    private static final DateTimeFormatter DUE_DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_name", nullable = false, length = 150)
    private String clientName;

    @Column(nullable = false, length = 10)
    private String utr;

    @Column(name = "client_type", nullable = false, length = 60)
    private String clientType;

    @Column(nullable = false, length = 100)
    private String industry;

    @Column(name = "accounting_period_start", nullable = false)
    private LocalDate accountingPeriodStart;

    @Column(name = "accounting_period_end", nullable = false)
    private LocalDate accountingPeriodEnd;

    @Column(name = "taxable_profit", nullable = false, precision = 12, scale = 2)
    private BigDecimal taxableProfit;

    @Column(name = "tax_payable", nullable = false, precision = 12, scale = 2)
    private BigDecimal taxPayable;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TaxReturnStatus status;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "assigned_to", nullable = false, length = 100)
    private String assignedTo;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    /** First letter of the first two words of the client's name, e.g. "John Miller Ltd" -> "JM". */
    public String getInitials() {
        if (clientName == null || clientName.isBlank()) {
            return "?";
        }
        StringBuilder sb = new StringBuilder();
        for (String word : clientName.trim().split("\\s+")) {
            if (sb.length() >= 2) break;
            if (!word.isEmpty() && Character.isLetter(word.charAt(0))) {
                sb.append(Character.toUpperCase(word.charAt(0)));
            }
        }
        return sb.length() > 0 ? sb.toString() : clientName.substring(0, 1).toUpperCase(Locale.ROOT);
    }

    public String getAccountingPeriodLabel() {
        if (accountingPeriodStart == null || accountingPeriodEnd == null) return "";
        return accountingPeriodStart.format(DATE_FORMAT) + " - " + accountingPeriodEnd.format(DATE_FORMAT);
    }

    public String getTaxableProfitLabel() {
        return taxableProfit == null ? "" : "£" + String.format(Locale.UK, "%,.0f", taxableProfit);
    }

    public String getTaxPayableLabel() {
        return taxPayable == null ? "" : "£" + String.format(Locale.UK, "%,.0f", taxPayable);
    }

    public String getDueDateLabel() {
        return dueDate == null ? "" : dueDate.format(DUE_DATE_FORMAT);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getUtr() { return utr; }
    public void setUtr(String utr) { this.utr = utr; }

    public String getClientType() { return clientType; }
    public void setClientType(String clientType) { this.clientType = clientType; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }

    public LocalDate getAccountingPeriodStart() { return accountingPeriodStart; }
    public void setAccountingPeriodStart(LocalDate accountingPeriodStart) { this.accountingPeriodStart = accountingPeriodStart; }

    public LocalDate getAccountingPeriodEnd() { return accountingPeriodEnd; }
    public void setAccountingPeriodEnd(LocalDate accountingPeriodEnd) { this.accountingPeriodEnd = accountingPeriodEnd; }

    public BigDecimal getTaxableProfit() { return taxableProfit; }
    public void setTaxableProfit(BigDecimal taxableProfit) { this.taxableProfit = taxableProfit; }

    public BigDecimal getTaxPayable() { return taxPayable; }
    public void setTaxPayable(BigDecimal taxPayable) { this.taxPayable = taxPayable; }

    public TaxReturnStatus getStatus() { return status; }
    public void setStatus(TaxReturnStatus status) { this.status = status; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
