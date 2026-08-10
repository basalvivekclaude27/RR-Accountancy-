package com.rraccountancy.app.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Entity
@Table(name = "inheritance_cases")
public class InheritanceCase {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_name", nullable = false, length = 150)
    private String clientName;

    @Column(nullable = false, length = 10)
    private String utr;

    @Column(name = "client_type", nullable = false, length = 60)
    private String clientType;

    @Column(name = "planning_type", nullable = false, length = 60)
    private String planningType;

    /** First year of the UK tax year, e.g. 2024 for "2024 - 2025". */
    @Column(name = "tax_year_start", nullable = false)
    private Integer taxYearStart;

    @Column(name = "estate_value", nullable = false, precision = 14, scale = 2)
    private BigDecimal estateValue;

    @Column(name = "nil_rate_band_available", nullable = false, precision = 12, scale = 2)
    private BigDecimal nilRateBandAvailable;

    @Column(name = "tax_liability", nullable = false, precision = 12, scale = 2)
    private BigDecimal taxLiability;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TaxReturnStatus status;

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
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now();
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

    public String getTaxYearLabel() {
        return taxYearStart == null ? "" : taxYearStart + " - " + (taxYearStart + 1);
    }

    public String getEstateValueLabel() {
        return estateValue == null ? "—" : "£" + String.format(Locale.UK, "%,.0f", estateValue);
    }

    public String getNilRateBandLabel() {
        return nilRateBandAvailable == null ? "—" : "£" + String.format(Locale.UK, "%,.0f", nilRateBandAvailable);
    }

    public String getTaxLiabilityLabel() {
        return taxLiability == null ? "£0" : "£" + String.format(Locale.UK, "%,.0f", taxLiability);
    }

    public String getLastUpdatedLabel() {
        return lastUpdated == null ? "—" : lastUpdated.format(DATE_FORMAT);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getUtr() { return utr; }
    public void setUtr(String utr) { this.utr = utr; }

    public String getClientType() { return clientType; }
    public void setClientType(String clientType) { this.clientType = clientType; }

    public String getPlanningType() { return planningType; }
    public void setPlanningType(String planningType) { this.planningType = planningType; }

    public Integer getTaxYearStart() { return taxYearStart; }
    public void setTaxYearStart(Integer taxYearStart) { this.taxYearStart = taxYearStart; }

    public BigDecimal getEstateValue() { return estateValue; }
    public void setEstateValue(BigDecimal estateValue) { this.estateValue = estateValue; }

    public BigDecimal getNilRateBandAvailable() { return nilRateBandAvailable; }
    public void setNilRateBandAvailable(BigDecimal nilRateBandAvailable) { this.nilRateBandAvailable = nilRateBandAvailable; }

    public BigDecimal getTaxLiability() { return taxLiability; }
    public void setTaxLiability(BigDecimal taxLiability) { this.taxLiability = taxLiability; }

    public TaxReturnStatus getStatus() { return status; }
    public void setStatus(TaxReturnStatus status) { this.status = status; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    public LocalDate getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDate lastUpdated) { this.lastUpdated = lastUpdated; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
