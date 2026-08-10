package com.rraccountancy.app.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Entity
@Table(name = "accounts_preparation_jobs")
public class AccountsPreparationJob {

    private static final DateTimeFormatter UPDATED_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_name", nullable = false, length = 150)
    private String clientName;

    @Column(name = "job_ref", nullable = false, length = 30)
    private String jobRef;

    /** First year of the financial year, e.g. 2024 for "2024 - 2025". */
    @Column(name = "financial_year_start", nullable = false)
    private Integer financialYearStart;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false, length = 30)
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private AccountStatus status;

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

    public String getFinancialYearLabel() {
        return financialYearStart == null ? "" : financialYearStart + " - " + (financialYearStart + 1);
    }

    public String getLastUpdatedLabel() {
        return lastUpdated == null ? "" : lastUpdated.format(UPDATED_FORMAT);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getJobRef() { return jobRef; }
    public void setJobRef(String jobRef) { this.jobRef = jobRef; }

    public Integer getFinancialYearStart() { return financialYearStart; }
    public void setFinancialYearStart(Integer financialYearStart) { this.financialYearStart = financialYearStart; }

    public AccountType getAccountType() { return accountType; }
    public void setAccountType(AccountType accountType) { this.accountType = accountType; }

    public AccountStatus getStatus() { return status; }
    public void setStatus(AccountStatus status) { this.status = status; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    public LocalDate getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDate lastUpdated) { this.lastUpdated = lastUpdated; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
