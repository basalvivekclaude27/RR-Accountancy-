package com.rraccountancy.app.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Entity
@Table(name = "business_plans")
public class BusinessPlan {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_name", nullable = false, length = 150)
    private String clientName;

    @Column(name = "plan_ref", nullable = false, length = 30)
    private String planRef;

    @Column(name = "plan_name", nullable = false, length = 150)
    private String planName;

    @Column(name = "plan_type", nullable = false, length = 100)
    private String planType;

    @Column(nullable = false, length = 100)
    private String industry;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private BusinessPlanStatus status;

    @Column(name = "revenue_potential", nullable = false, precision = 14, scale = 2)
    private BigDecimal revenuePotential;

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

    /** First letter of the first two words of the client's name, e.g. "John Miller" -> "JM". */
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

    public String getRevenuePotentialLabel() {
        return revenuePotential == null ? "" : "£" + String.format(Locale.UK, "%,.0f", revenuePotential);
    }

    public String getLastUpdatedLabel() {
        return lastUpdated == null ? "" : lastUpdated.format(DATE_FORMAT);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getPlanRef() { return planRef; }
    public void setPlanRef(String planRef) { this.planRef = planRef; }

    public String getPlanName() { return planName; }
    public void setPlanName(String planName) { this.planName = planName; }

    public String getPlanType() { return planType; }
    public void setPlanType(String planType) { this.planType = planType; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }

    public BusinessPlanStatus getStatus() { return status; }
    public void setStatus(BusinessPlanStatus status) { this.status = status; }

    public BigDecimal getRevenuePotential() { return revenuePotential; }
    public void setRevenuePotential(BigDecimal revenuePotential) { this.revenuePotential = revenuePotential; }

    public LocalDate getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDate lastUpdated) { this.lastUpdated = lastUpdated; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
