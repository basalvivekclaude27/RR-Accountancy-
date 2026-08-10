package com.rraccountancy.app.service;

import com.rraccountancy.app.domain.BookkeepingJob;
import com.rraccountancy.app.dto.BookkeepingJobFilter;
import com.rraccountancy.app.dto.BookkeepingJobForm;
import com.rraccountancy.app.repository.BookkeepingJobRepository;
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
public class BookkeepingJobService {

    private static final int PAGE_SIZE = 6;

    private final BookkeepingJobRepository repository;

    public BookkeepingJobService(BookkeepingJobRepository repository) {
        this.repository = repository;
    }

    public Page<BookkeepingJob> search(BookkeepingJobFilter filter) {
        Specification<BookkeepingJob> spec = buildSpecification(filter);
        PageRequest pageRequest = PageRequest.of(filter.getPage(), PAGE_SIZE, Sort.by(Sort.Direction.DESC, "lastUpdated"));
        return repository.findAll(spec, pageRequest);
    }

    public List<String> allClientNames() {
        return repository.findDistinctClientNames();
    }

    public List<String> allAssignees() {
        return repository.findDistinctAssignees();
    }

    public BookkeepingJob get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bookkeeping job not found: " + id));
    }

    @Transactional
    public BookkeepingJob create(BookkeepingJobForm form) {
        BookkeepingJob job = new BookkeepingJob();
        job.setInvoiceRef(nextInvoiceRef());
        applyForm(job, form);
        return repository.save(job);
    }

    @Transactional
    public BookkeepingJob update(Long id, BookkeepingJobForm form) {
        BookkeepingJob job = get(id);
        applyForm(job, form);
        return repository.save(job);
    }

    private void applyForm(BookkeepingJob job, BookkeepingJobForm form) {
        job.setClientName(form.getClientName().trim());
        if (form.getInvoiceRef() != null && !form.getInvoiceRef().isBlank()) {
            job.setInvoiceRef(form.getInvoiceRef().trim());
        }
        job.setPeriodDate(form.getPeriod().atDay(1));
        job.setCategory(form.getCategory());
        job.setStatus(form.getStatus());
        job.setAssignedTo(form.getAssignedTo().trim());
    }

    private String nextInvoiceRef() {
        long count = repository.count();
        return String.format(Locale.ROOT, "INV-%04d", 1006 + count);
    }

    private Specification<BookkeepingJob> buildSpecification(BookkeepingJobFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.hasClient()) {
                predicates.add(cb.equal(root.get("clientName"), filter.getClient()));
            }
            if (filter.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), filter.getStatus()));
            }
            if (filter.getCategory() != null) {
                predicates.add(cb.equal(root.get("category"), filter.getCategory()));
            }
            if (filter.hasAssignedTo()) {
                predicates.add(cb.equal(root.get("assignedTo"), filter.getAssignedTo()));
            }
            if (filter.getPeriod() != null) {
                YearMonth ym = filter.getPeriod();
                LocalDate from = ym.atDay(1);
                LocalDate to = ym.atEndOfMonth();
                predicates.add(cb.between(root.get("periodDate"), from, to));
            }
            if (filter.hasQuery()) {
                String like = "%" + filter.getQ().trim().toLowerCase(Locale.ROOT) + "%";
                predicates.add(cb.or(
                        cb.like(cb.lower(root.get("clientName")), like),
                        cb.like(cb.lower(root.get("invoiceRef")), like)
                ));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
