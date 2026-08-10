package com.rraccountancy.app.service;

import com.rraccountancy.app.domain.CompanyFormationEnquiry;
import com.rraccountancy.app.dto.CompanyFormationFilter;
import com.rraccountancy.app.dto.CompanyFormationForm;
import com.rraccountancy.app.repository.CompanyFormationEnquiryRepository;
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
public class CompanyFormationService {

    private static final int PAGE_SIZE = 6;

    private final CompanyFormationEnquiryRepository repository;

    public CompanyFormationService(CompanyFormationEnquiryRepository repository) {
        this.repository = repository;
    }

    public Page<CompanyFormationEnquiry> search(CompanyFormationFilter filter) {
        Specification<CompanyFormationEnquiry> spec = buildSpecification(filter);
        PageRequest pageRequest = PageRequest.of(filter.getPage(), PAGE_SIZE, Sort.by(Sort.Direction.DESC, "enquiryDate"));
        return repository.findAll(spec, pageRequest);
    }

    public List<String> allCompanyTypes() {
        return repository.findDistinctCompanyTypes();
    }

    public List<String> allJurisdictions() {
        return repository.findDistinctJurisdictions();
    }

    public CompanyFormationEnquiry get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Company formation enquiry not found: " + id));
    }

    @Transactional
    public CompanyFormationEnquiry create(CompanyFormationForm form) {
        CompanyFormationEnquiry enquiry = new CompanyFormationEnquiry();
        enquiry.setEnquiryRef(nextEnquiryRef());
        applyForm(enquiry, form);
        return repository.save(enquiry);
    }

    @Transactional
    public CompanyFormationEnquiry update(Long id, CompanyFormationForm form) {
        CompanyFormationEnquiry enquiry = get(id);
        applyForm(enquiry, form);
        return repository.save(enquiry);
    }

    private void applyForm(CompanyFormationEnquiry enquiry, CompanyFormationForm form) {
        enquiry.setClientName(form.getClientName().trim());
        if (form.getEnquiryRef() != null && !form.getEnquiryRef().isBlank()) {
            enquiry.setEnquiryRef(form.getEnquiryRef().trim());
        }
        enquiry.setCompanyName(form.getCompanyName().trim());
        enquiry.setCompanyType(form.getCompanyType().trim());
        enquiry.setJurisdiction(form.getJurisdiction().trim());
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

    private Specification<CompanyFormationEnquiry> buildSpecification(CompanyFormationFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getSource() != null) {
                predicates.add(cb.equal(root.get("source"), filter.getSource()));
            }
            if (filter.hasCompanyType()) {
                predicates.add(cb.equal(root.get("companyType"), filter.getCompanyType()));
            }
            if (filter.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), filter.getStatus()));
            }
            if (filter.hasJurisdiction()) {
                predicates.add(cb.equal(root.get("jurisdiction"), filter.getJurisdiction()));
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
                        cb.like(cb.lower(root.get("companyName")), like)
                ));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
