package com.rraccountancy.app.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Entity
@Table(name = "vat_returns")
public class VatReturn {

    private static final DateTimeFormatter MONTH_FORMAT = DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH);
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_name", nullable = false, length = 150)
    private String clientName;

    @Column(name = "vat_number", nullable = false, length = 20)
    private String vatNumber;

    @Column(name = "scheme_type", nullable = false, length = 60)
    private String schemeType;

    @Column(name = "client_type", nullable = false, length = 60)
    private String clientType;

    /** First day of the VAT quarter, e.g. 2025-04-01 for "Apr - Jun 2025". */
    @Column(name = "period_start", nullable = false)
    private LocalDate periodStart;

    @Column(name = "sales_ex_vat", precision = 12, scale = 2)
    private BigDecimal salesExVat;

    @Column(name = "purchases_ex_vat", precision = 12, scale = 2)
    private BigDecimal purchasesExVat;

    /** Positive = payable to HMRC, negative = refund due, null = not yet calculated. */
    @Column(name = "vat_amount", precision = 12, scale = 2)
    private BigDecimal vatAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TaxReturnStatus status;

    @Column(name = "filed_on")
    private LocalDate filedOn;

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

    public String getPeriodLabel() {
        if (periodStart == null) return "";
        LocalDate end = periodStart.plusMonths(2);
        return periodStart.format(MONTH_FORMAT) + " - " + end.format(MONTH_FORMAT) + " " + end.getYear();
    }

    /** UK-style fiscal quarter label, e.g. "(Q1 2025/26)" for a period starting April. */
    public String getFiscalQuarterLabel() {
        if (periodStart == null) return "";
        int month = periodStart.getMonthValue();
        int quarter;
        int fyStart;
        if (month >= 4 && month <= 6) { quarter = 1; fyStart = periodStart.getYear(); }
        else if (month >= 7 && month <= 9) { quarter = 2; fyStart = periodStart.getYear(); }
        else if (month >= 10 && month <= 12) { quarter = 3; fyStart = periodStart.getYear(); }
        else { quarter = 4; fyStart = periodStart.getYear() - 1; }
        return "(Q" + quarter + " " + fyStart + "/" + String.format("%02d", (fyStart + 1) % 100) + ")";
    }

    public String getSalesLabel() {
        return salesExVat == null ? "—" : "£" + String.format(Locale.UK, "%,.0f", salesExVat);
    }

    public String getPurchasesLabel() {
        return purchasesExVat == null ? "—" : "£" + String.format(Locale.UK, "%,.0f", purchasesExVat);
    }

    public String getVatAmountLabel() {
        if (vatAmount == null) return "—";
        String formatted = "£" + String.format(Locale.UK, "%,.0f", vatAmount.abs());
        return vatAmount.signum() < 0 ? "(" + formatted + ")" : formatted;
    }

    public String getFiledOnLabel() {
        return filedOn == null ? "—" : filedOn.format(DATE_FORMAT);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getVatNumber() { return vatNumber; }
    public void setVatNumber(String vatNumber) { this.vatNumber = vatNumber; }

    public String getSchemeType() { return schemeType; }
    public void setSchemeType(String schemeType) { this.schemeType = schemeType; }

    public String getClientType() { return clientType; }
    public void setClientType(String clientType) { this.clientType = clientType; }

    public LocalDate getPeriodStart() { return periodStart; }
    public void setPeriodStart(LocalDate periodStart) { this.periodStart = periodStart; }

    public BigDecimal getSalesExVat() { return salesExVat; }
    public void setSalesExVat(BigDecimal salesExVat) { this.salesExVat = salesExVat; }

    public BigDecimal getPurchasesExVat() { return purchasesExVat; }
    public void setPurchasesExVat(BigDecimal purchasesExVat) { this.purchasesExVat = purchasesExVat; }

    public BigDecimal getVatAmount() { return vatAmount; }
    public void setVatAmount(BigDecimal vatAmount) { this.vatAmount = vatAmount; }

    public TaxReturnStatus getStatus() { return status; }
    public void setStatus(TaxReturnStatus status) { this.status = status; }

    public LocalDate getFiledOn() { return filedOn; }
    public void setFiledOn(LocalDate filedOn) { this.filedOn = filedOn; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
