package com.rraccountancy.app.service;

import com.rraccountancy.app.domain.StartupEnquiry;
import com.rraccountancy.app.dto.StartupEnquiryFilter;
import com.rraccountancy.app.dto.StartupEnquiryForm;
import com.rraccountancy.app.repository.StartupEnquiryRepository;
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
public class StartupEnquiryService {

    private static final int PAGE_SIZE = 6;

    private final StartupEnquiryRepository repository;

    public StartupEnquiryService(StartupEnquiryRepository repository) {
        this.repository = repository;
    }

    public Page<StartupEnquiry> search(StartupEnquiryFilter filter) {
        Specification<StartupEnquiry> spec = buildSpecification(filter);
        PageRequest pageRequest = PageRequest.of(filter.getPage(), PAGE_SIZE, Sort.by(Sort.Direction.DESC, "enquiryDate"));
        return repository.findAll(spec, pageRequest);
    }

    public List<String> allBusinessTypes() {
        return repository.findDistinctBusinessTypes();
    }

    public List<String> allAssignees() {
        return repository.findDistinctAssignees();
    }

    public StartupEnquiry get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Startup enquiry not found: " + id));
    }

    @Transactional
    public StartupEnquiry create(StartupEnquiryForm form) {
        StartupEnquiry enquiry = new StartupEnquiry();
        enquiry.setEnquiryRef(nextEnquiryRef());
        applyForm(enquiry, form);
        return repository.save(enquiry);
    }

    @Transactional
    public StartupEnquiry update(Long id, StartupEnquiryForm form) {
        StartupEnquiry enquiry = get(id);
        applyForm(enquiry, form);
        return repository.save(enquiry);
    }

    private void applyForm(StartupEnquiry enquiry, StartupEnquiryForm form) {
        enquiry.setClientName(form.getClientName().trim());
        if (form.getEnquiryRef() != null && !form.getEnquiryRef().isBlank()) {
            enquiry.setEnquiryRef(form.getEnquiryRef().trim());
        }
        enquiry.setBusinessName(form.getBusinessName().trim());
        enquiry.setBusinessType(form.getBusinessType().trim());
        enquiry.setStage(form.getStage());
        enquiry.setStatus(form.getStatus());
        enquiry.setSource(form.getSource());
        enquiry.setAssignedTo(form.getAssignedTo().trim());
        enquiry.setEnquiryDate(form.getEnquiryDate());
    }

    private String nextEnquiryRef() {
        long count = repository.count();
        return String.format(Locale.ROOT, "ENQ-%04d", 1007 + count);
    }

    private Specification<StartupEnquiry> buildSpecification(StartupEnquiryFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getSource() != null) {
                predicates.add(cb.equal(root.get("source"), filter.getSource()));
            }
            if (filter.getStage() != null) {
                predicates.add(cb.equal(root.get("stage"), filter.getStage()));
            }
            if (filter.hasBusinessType()) {
                predicates.add(cb.equal(root.get("businessType"), filter.getBusinessType()));
            }
            if (filter.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), filter.getStatus()));
            }
            if (filter.hasAssignedTo()) {
                predicates.add(cb.equal(root.get("assignedTo"), filter.getAssignedTo()));
            }
            if (filter.getDateFrom() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("enquiryDate"), filter.getDateFrom()));
            }
            if (filter.getDateTo() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("enquiryDate"), filter.getDateTo()));
            }
            if (filter.hasQuery()) {
                String like = "%" + filter.getQ().trim().toLowerCase(Locale.ROOT) + "%";
                predicates.add(cb.or(
                        cb.like(cb.lower(root.get("clientName")), like),
                        cb.like(cb.lower(root.get("businessName")), like)
                ));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
