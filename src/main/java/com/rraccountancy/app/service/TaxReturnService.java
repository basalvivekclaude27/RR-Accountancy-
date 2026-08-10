package com.rraccountancy.app.service;

import com.rraccountancy.app.domain.TaxReturn;
import com.rraccountancy.app.dto.TaxReturnFilter;
import com.rraccountancy.app.dto.TaxReturnForm;
import com.rraccountancy.app.repository.TaxReturnRepository;
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
public class TaxReturnService {

    private static final int PAGE_SIZE = 6;

    private final TaxReturnRepository repository;

    public TaxReturnService(TaxReturnRepository repository) {
        this.repository = repository;
    }

    public Page<TaxReturn> search(TaxReturnFilter filter) {
        Specification<TaxReturn> spec = buildSpecification(filter);
        PageRequest pageRequest = PageRequest.of(filter.getPage(), PAGE_SIZE, Sort.by(Sort.Direction.DESC, "lastUpdated"));
        return repository.findAll(spec, pageRequest);
    }

    public List<String> allClientTypes() {
        return repository.findDistinctClientTypes();
    }

    public List<String> allAssignees() {
        return repository.findDistinctAssignees();
    }

    public List<Integer> allTaxYears() {
        return repository.findDistinctTaxYears();
    }

    public TaxReturn get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tax return not found: " + id));
    }

    @Transactional
    public TaxReturn create(TaxReturnForm form) {
        TaxReturn taxReturn = new TaxReturn();
        applyForm(taxReturn, form);
        return repository.save(taxReturn);
    }

    @Transactional
    public TaxReturn update(Long id, TaxReturnForm form) {
        TaxReturn taxReturn = get(id);
        applyForm(taxReturn, form);
        return repository.save(taxReturn);
    }

    private void applyForm(TaxReturn taxReturn, TaxReturnForm form) {
        taxReturn.setClientName(form.getClientName().trim());
        taxReturn.setUtr(form.getUtr().trim());
        taxReturn.setClientType(form.getClientType().trim());
        taxReturn.setTaxYearStart(form.getTaxYearStart());
        taxReturn.setStatus(form.getStatus());
        taxReturn.setRefundAmount(form.getRefundAmount());
        taxReturn.setAssignedTo(form.getAssignedTo().trim());
    }

    private Specification<TaxReturn> buildSpecification(TaxReturnFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getTaxYear() != null) {
                predicates.add(cb.equal(root.get("taxYearStart"), filter.getTaxYear()));
            }
            if (filter.hasClientType()) {
                predicates.add(cb.equal(root.get("clientType"), filter.getClientType()));
            }
            if (filter.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), filter.getStatus()));
            }
            if (filter.hasAssignedTo()) {
                predicates.add(cb.equal(root.get("assignedTo"), filter.getAssignedTo()));
            }
            if (filter.getDateFrom() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("lastUpdated"), filter.getDateFrom()));
            }
            if (filter.getDateTo() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("lastUpdated"), filter.getDateTo()));
            }
            if (filter.hasQuery()) {
                String like = "%" + filter.getQ().trim().toLowerCase(Locale.ROOT) + "%";
                predicates.add(cb.or(
                        cb.like(cb.lower(root.get("clientName")), like),
                        cb.like(root.get("utr"), filter.getQ().trim())
                ));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
