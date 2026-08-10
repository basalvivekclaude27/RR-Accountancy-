package com.rraccountancy.app.service;

import com.rraccountancy.app.domain.PayrollRun;
import com.rraccountancy.app.dto.PayrollRunFilter;
import com.rraccountancy.app.dto.PayrollRunForm;
import com.rraccountancy.app.repository.PayrollRunRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

@Service
public class PayrollRunService {

    private static final int PAGE_SIZE = 6;

    private final PayrollRunRepository repository;

    public PayrollRunService(PayrollRunRepository repository) {
        this.repository = repository;
    }

    public Page<PayrollRun> search(PayrollRunFilter filter) {
        Specification<PayrollRun> spec = buildSpecification(filter);
        PageRequest pageRequest = PageRequest.of(filter.getPage(), PAGE_SIZE, Sort.by(Sort.Direction.DESC, "lastUpdated"));
        return repository.findAll(spec, pageRequest);
    }

    public List<String> allClientNames() {
        return repository.findDistinctClientNames();
    }

    public List<String> allAssignees() {
        return repository.findDistinctAssignees();
    }

    public PayrollRun get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Payroll run not found: " + id));
    }

    @Transactional
    public PayrollRun create(PayrollRunForm form) {
        PayrollRun run = new PayrollRun();
        applyForm(run, form);
        return repository.save(run);
    }

    @Transactional
    public PayrollRun update(Long id, PayrollRunForm form) {
        PayrollRun run = get(id);
        applyForm(run, form);
        return repository.save(run);
    }

    private void applyForm(PayrollRun run, PayrollRunForm form) {
        run.setClientName(form.getClientName().trim());
        run.setPayrollMonth(form.getPayrollMonth().atDay(1));
        run.setFrequency(form.getFrequency());
        run.setEmployees(form.getEmployees());
        run.setNetPay(form.getNetPay());
        run.setStatus(form.getStatus());
        run.setAssignedTo(form.getAssignedTo().trim());
    }

    private Specification<PayrollRun> buildSpecification(PayrollRunFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.hasClient()) {
                predicates.add(cb.equal(root.get("clientName"), filter.getClient()));
            }
            if (filter.getFrequency() != null) {
                predicates.add(cb.equal(root.get("frequency"), filter.getFrequency()));
            }
            if (filter.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), filter.getStatus()));
            }
            if (filter.hasAssignedTo()) {
                predicates.add(cb.equal(root.get("assignedTo"), filter.getAssignedTo()));
            }
            if (filter.getMonth() != null) {
                YearMonth ym = filter.getMonth();
                predicates.add(cb.between(root.get("payrollMonth"), ym.atDay(1), ym.atEndOfMonth()));
            }
            if (filter.hasQuery()) {
                String like = "%" + filter.getQ().trim().toLowerCase(Locale.ROOT) + "%";
                predicates.add(cb.like(cb.lower(root.get("clientName")), like));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
