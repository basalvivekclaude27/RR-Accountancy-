package com.rraccountancy.app.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Entity
@Table(name = "company_formation_enquiries")
public class CompanyFormationEnquiry {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_name", nullable = false, length = 150)
    private String clientName;

    @Column(name = "enquiry_ref", nullable = false, length = 30)
    private String enquiryRef;

    @Column(name = "company_name", nullable = false, length = 150)
    private String companyName;

    @Column(name = "company_type", nullable = false, length = 100)
    private String companyType;

    @Column(nullable = false, length = 50)
    private String jurisdiction;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private CompanyFormationStage stage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private CompanyFormationStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EnquirySource source;

    @Column(name = "assigned_to", nullable = false, length = 100)
    private String assignedTo;

    @Column(name = "enquiry_date", nullable = false)
    private LocalDate enquiryDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        if (this.enquiryDate == null) {
            this.enquiryDate = now.toLocalDate();
        }
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

    public String getEnquiryDateLabel() {
        return enquiryDate == null ? "" : enquiryDate.format(DATE_FORMAT);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getEnquiryRef() { return enquiryRef; }
    public void setEnquiryRef(String enquiryRef) { this.enquiryRef = enquiryRef; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getCompanyType() { return companyType; }
    public void setCompanyType(String companyType) { this.companyType = companyType; }

    public String getJurisdiction() { return jurisdiction; }
    public void setJurisdiction(String jurisdiction) { this.jurisdiction = jurisdiction; }

    public CompanyFormationStage getStage() { return stage; }
    public void setStage(CompanyFormationStage stage) { this.stage = stage; }

    public CompanyFormationStatus getStatus() { return status; }
    public void setStatus(CompanyFormationStatus status) { this.status = status; }

    public EnquirySource getSource() { return source; }
    public void setSource(EnquirySource source) { this.source = source; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    public LocalDate getEnquiryDate() { return enquiryDate; }
    public void setEnquiryDate(LocalDate enquiryDate) { this.enquiryDate = enquiryDate; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
