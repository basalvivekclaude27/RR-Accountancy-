package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.EnquirySource;
import com.rraccountancy.app.domain.StartupStage;
import com.rraccountancy.app.domain.StartupStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class StartupEnquiryForm {

    @NotBlank(message = "Client name is required")
    private String clientName;

    /** Optional — StartupEnquiryService auto-generates one (e.g. ENQ-1025) when left blank. */
    private String enquiryRef;

    @NotBlank(message = "Business name is required")
    private String businessName;

    @NotBlank(message = "Business type is required")
    private String businessType;

    @NotNull(message = "Stage is required")
    private StartupStage stage;

    @NotNull(message = "Status is required")
    private StartupStatus status;

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

    public String getBusinessName() { return businessName; }
    public void setBusinessName(String businessName) { this.businessName = businessName; }

    public String getBusinessType() { return businessType; }
    public void setBusinessType(String businessType) { this.businessType = businessType; }

    public StartupStage getStage() { return stage; }
    public void setStage(StartupStage stage) { this.stage = stage; }

    public StartupStatus getStatus() { return status; }
    public void setStatus(StartupStatus status) { this.status = status; }

    public EnquirySource getSource() { return source; }
    public void setSource(EnquirySource source) { this.source = source; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    public LocalDate getEnquiryDate() { return enquiryDate; }
    public void setEnquiryDate(LocalDate enquiryDate) { this.enquiryDate = enquiryDate; }
}
