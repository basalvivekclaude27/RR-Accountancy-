package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.CompanyFormationStage;
import com.rraccountancy.app.domain.CompanyFormationStatus;
import com.rraccountancy.app.domain.EnquirySource;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class CompanyFormationForm {

    @NotBlank(message = "Client name is required")
    private String clientName;

    /** Optional — CompanyFormationService auto-generates one (e.g. ENQ-1075) when left blank. */
    private String enquiryRef;

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Company type is required")
    private String companyType;

    @NotBlank(message = "Jurisdiction is required")
    private String jurisdiction;

    @NotNull(message = "Stage is required")
    private CompanyFormationStage stage;

    @NotNull(message = "Status is required")
    private CompanyFormationStatus status;

    @NotNull(message = "Source is required")
    private EnquirySource source;

    @NotBlank(message = "Assigned staff member is required")
    private String assignedTo;

    @NotNull(message = "Enquiry date is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate enquiryDate;

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
}
