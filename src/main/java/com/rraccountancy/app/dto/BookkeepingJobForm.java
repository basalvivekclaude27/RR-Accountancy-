package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.JobCategory;
import com.rraccountancy.app.domain.JobStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.YearMonth;

public class BookkeepingJobForm {

    @NotBlank(message = "Client / business name is required")
    private String clientName;

    /** Optional — BookkeepingJobService auto-generates one (e.g. INV-1007) when left blank. */
    private String invoiceRef;

    @NotNull(message = "Period is required")
    @DateTimeFormat(pattern = "yyyy-MM")
    private YearMonth period;

    @NotNull(message = "Category is required")
    private JobCategory category;

    @NotNull(message = "Status is required")
    private JobStatus status;

    @NotBlank(message = "Assigned staff member is required")
    private String assignedTo;

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getInvoiceRef() { return invoiceRef; }
    public void setInvoiceRef(String invoiceRef) { this.invoiceRef = invoiceRef; }

    public YearMonth getPeriod() { return period; }
    public void setPeriod(YearMonth period) { this.period = period; }

    public JobCategory getCategory() { return category; }
    public void setCategory(JobCategory category) { this.category = category; }

    public JobStatus getStatus() { return status; }
    public void setStatus(JobStatus status) { this.status = status; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }
}
