package com.rraccountancy.app.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Entity
@Table(name = "bookkeeping_jobs")
public class BookkeepingJob {

    private static final DateTimeFormatter PERIOD_FORMAT = DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH);
    private static final DateTimeFormatter UPDATED_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_name", nullable = false, length = 150)
    private String clientName;

    @Column(name = "invoice_ref", nullable = false, length = 30)
    private String invoiceRef;

    @Column(name = "period_date", nullable = false)
    private LocalDate periodDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private JobCategory category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private JobStatus status;

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

    /** First letter of up to the first three words of the client name, e.g. "ABC Solutions Ltd." -> "ABC". */
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

    public String getLastUpdatedLabel() {
        return lastUpdated == null ? "" : lastUpdated.format(UPDATED_FORMAT);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getInvoiceRef() { return invoiceRef; }
    public void setInvoiceRef(String invoiceRef) { this.invoiceRef = invoiceRef; }

    public LocalDate getPeriodDate() { return periodDate; }
    public void setPeriodDate(LocalDate periodDate) { this.periodDate = periodDate; }

    public JobCategory getCategory() { return category; }
    public void setCategory(JobCategory category) { this.category = category; }

    public JobStatus getStatus() { return status; }
    public void setStatus(JobStatus status) { this.status = status; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    public LocalDate getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDate lastUpdated) { this.lastUpdated = lastUpdated; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
