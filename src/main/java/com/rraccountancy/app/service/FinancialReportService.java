package com.rraccountancy.app.service;

import com.rraccountancy.app.domain.FinancialReport;
import com.rraccountancy.app.dto.FinancialReportFilter;
import com.rraccountancy.app.dto.FinancialReportForm;
import com.rraccountancy.app.repository.FinancialReportRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

@Service
public class FinancialReportService {

    private static final int PAGE_SIZE = 6;

    private final FinancialReportRepository repository;

    public FinancialReportService(FinancialReportRepository repository) {
        this.repository = repository;
    }

    public Page<FinancialReport> search(FinancialReportFilter filter) {
        Specification<FinancialReport> spec = buildSpecification(filter);
        PageRequest pageRequest = PageRequest.of(filter.getPage(), PAGE_SIZE, Sort.by(Sort.Direction.DESC, "generatedOn"));
        return repository.findAll(spec, pageRequest);
    }

    public List<String> allClientNames() {
        return repository.findDistinctClientNames();
    }

    public List<Integer> allFinancialYears() {
        return repository.findDistinctFinancialYears();
    }

    public FinancialReport get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Financial report not found: " + id));
    }

    @Transactional
    public FinancialReport create(FinancialReportForm form) {
        FinancialReport report = new FinancialReport();
        applyForm(report, form);
        return repository.save(report);
    }

    @Transactional
    public FinancialReport update(Long id, FinancialReportForm form) {
        FinancialReport report = get(id);
        applyForm(report, form);
        return repository.save(report);
    }

    private void applyForm(FinancialReport report, FinancialReportForm form) {
        report.setClientName(form.getClientName().trim());
        report.setReportType(form.getReportType());
        report.setPeriodDate(form.getPeriod().atDay(1));
        report.setFinancialYearStart(form.getFinancialYearStart());
        report.setStatus(form.getStatus());
    }

    private Specification<FinancialReport> buildSpecification(FinancialReportFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.hasClient()) {
                predicates.add(cb.equal(root.get("clientName"), filter.getClient()));
            }
            if (filter.getFinancialYear() != null) {
                predicates.add(cb.equal(root.get("financialYearStart"), filter.getFinancialYear()));
            }
            if (filter.getReportType() != null) {
                predicates.add(cb.equal(root.get("reportType"), filter.getReportType()));
            }
            if (filter.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), filter.getStatus()));
            }
            if (filter.getPeriod() != null) {
                YearMonth ym = filter.getPeriod();
                predicates.add(cb.between(root.get("periodDate"), ym.atDay(1), ym.atEndOfMonth()));
            }
            if (filter.hasQuery()) {
                String like = "%" + filter.getQ().trim().toLowerCase(Locale.ROOT) + "%";
                predicates.add(cb.like(cb.lower(root.get("clientName")), like));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
