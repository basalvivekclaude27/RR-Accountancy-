package com.rraccountancy.app.dto;

import com.rraccountancy.app.domain.ServiceChargeStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.YearMonth;

/** Query-string-bound filter state for the Service Charge Accounts list. */
public class ServiceChargeFilter {

    private String property;
    private Integer financialYear;
    private ServiceChargeStatus status;

    /** Same enum as status — spec shows a separate "Collection Status" filter alongside "Status". */
    private ServiceChargeStatus collectionStatus;

    @DateTimeFormat(pattern = "yyyy-MM")
    private YearMonth period;

    private String q;
    private int page = 0;

    public boolean hasProperty() { return property != null && !property.isBlank(); }
    public boolean hasQuery() { return q != null && !q.isBlank(); }

    public String getProperty() { return property; }
    public void setProperty(String property) { this.property = property; }

    public Integer getFinancialYear() { return financialYear; }
    public void setFinancialYear(Integer financialYear) { this.financialYear = financialYear; }

    public ServiceChargeStatus getStatus() { return status; }
    public void setStatus(ServiceChargeStatus status) { this.status = status; }

    public ServiceChargeStatus getCollectionStatus() { return collectionStatus; }
    public void setCollectionStatus(ServiceChargeStatus collectionStatus) { this.collectionStatus = collectionStatus; }

    public YearMonth getPeriod() { return period; }
    public void setPeriod(YearMonth period) { this.period = period; }

    public String getQ() { return q; }
    public void setQ(String q) { this.q = q; }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = Math.max(page, 0); }
}
