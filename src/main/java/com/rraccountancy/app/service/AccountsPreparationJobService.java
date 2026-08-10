package com.rraccountancy.app.service;

import com.rraccountancy.app.domain.AccountsPreparationJob;
import com.rraccountancy.app.dto.AccountsPreparationJobFilter;
import com.rraccountancy.app.dto.AccountsPreparationJobForm;
import com.rraccountancy.app.repository.AccountsPreparationJobRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

@Service
public class AccountsPreparationJobService {

    private static final int PAGE_SIZE = 6;

    private final AccountsPreparationJobRepository repository;

    public AccountsPreparationJobService(AccountsPreparationJobRepository repository) {
        this.repository = repository;
    }

    public Page<AccountsPreparationJob> search(AccountsPreparationJobFilter filter) {
        Specification<AccountsPreparationJob> spec = buildSpecification(filter);
        PageRequest pageRequest = PageRequest.of(filter.getPage(), PAGE_SIZE, Sort.by(Sort.Direction.DESC, "lastUpdated"));
        return repository.findAll(spec, pageRequest);
    }

    public List<String> allClientNames() {
        return repository.findDistinctClientNames();
    }

    public List<String> allAssignees() {
        return repository.findDistinctAssignees();
    }

    public List<Integer> allFinancialYears() {
        return repository.findDistinctFinancialYears();
    }

    public AccountsPreparationJob get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Accounts preparation job not found: " + id));
    }

    @Transactional
    public AccountsPreparationJob create(AccountsPreparationJobForm form) {
        AccountsPreparationJob job = new AccountsPreparationJob();
        job.setJobRef(nextJobRef());
        applyForm(job, form);
        return repository.save(job);
    }

    @Transactional
    public AccountsPreparationJob update(Long id, AccountsPreparationJobForm form) {
        AccountsPreparationJob job = get(id);
        applyForm(job, form);
        return repository.save(job);
    }

    private void applyForm(AccountsPreparationJob job, AccountsPreparationJobForm form) {
        job.setClientName(form.getClientName().trim());
        if (form.getJobRef() != null && !form.getJobRef().isBlank()) {
            job.setJobRef(form.getJobRef().trim());
        }
        job.setFinancialYearStart(form.getFinancialYearStart());
        job.setAccountType(form.getAccountType());
        job.setStatus(form.getStatus());
        job.setAssignedTo(form.getAssignedTo().trim());
    }

    private String nextJobRef() {
        long count = repository.count();
        return String.format(Locale.ROOT, "ACC-%04d", 1007 + count);
    }

    private Specification<AccountsPreparationJob> buildSpecification(AccountsPreparationJobFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.hasClient()) {
                predicates.add(cb.equal(root.get("clientName"), filter.getClient()));
            }
            if (filter.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), filter.getStatus()));
            }
            if (filter.getAccountType() != null) {
                predicates.add(cb.equal(root.get("accountType"), filter.getAccountType()));
            }
            if (filter.hasAssignedTo()) {
                predicates.add(cb.equal(root.get("assignedTo"), filter.getAssignedTo()));
            }
            if (filter.getFinancialYear() != null) {
                predicates.add(cb.equal(root.get("financialYearStart"), filter.getFinancialYear()));
            }
            if (filter.hasQuery()) {
                String like = "%" + filter.getQ().trim().toLowerCase(Locale.ROOT) + "%";
                predicates.add(cb.or(
                        cb.like(cb.lower(root.get("clientName")), like),
                        cb.like(cb.lower(root.get("jobRef")), like)
                ));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
