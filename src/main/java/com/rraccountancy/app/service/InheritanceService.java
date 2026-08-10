package com.rraccountancy.app.service;

import com.rraccountancy.app.domain.InheritanceCase;
import com.rraccountancy.app.dto.InheritanceFilter;
import com.rraccountancy.app.dto.InheritanceForm;
import com.rraccountancy.app.repository.InheritanceCaseRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

@Service
public class InheritanceService {

    private static final int PAGE_SIZE = 6;

    private final InheritanceCaseRepository repository;

    public InheritanceService(InheritanceCaseRepository repository) {
        this.repository = repository;
    }

    public Page<InheritanceCase> search(InheritanceFilter filter) {
        Specification<InheritanceCase> spec = buildSpecification(filter);
        Sort sort = Sort.by(Sort.Direction.DESC, "lastUpdated").and(Sort.by(Sort.Direction.ASC, "id"));
        PageRequest pageRequest = PageRequest.of(filter.getPage(), PAGE_SIZE, sort);
        return repository.findAll(spec, pageRequest);
    }

    public List<String> allClientTypes() {
        return repository.findDistinctClientTypes();
    }

    public List<String> allPlanningTypes() {
        return repository.findDistinctPlanningTypes();
    }

    public List<Integer> allTaxYears() {
        return repository.findDistinctTaxYears();
    }

    public InheritanceCase get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("IHT case not found: " + id));
    }

    @Transactional
    public InheritanceCase create(InheritanceForm form) {
        InheritanceCase iht = new InheritanceCase();
        applyForm(iht, form);
        return repository.save(iht);
    }

    @Transactional
    public InheritanceCase update(Long id, InheritanceForm form) {
        InheritanceCase iht = get(id);
        applyForm(iht, form);
        return repository.save(iht);
    }

    private void applyForm(InheritanceCase iht, InheritanceForm form) {
        iht.setClientName(form.getClientName().trim());
        iht.setUtr(form.getUtr().trim());
        iht.setClientType(form.getClientType().trim());
        iht.setPlanningType(form.getPlanningType().trim());
        iht.setTaxYearStart(form.getTaxYearStart());
        iht.setEstateValue(form.getEstateValue());
        iht.setNilRateBandAvailable(form.getNilRateBandAvailable());
        iht.setTaxLiability(form.getTaxLiability());
        iht.setStatus(form.getStatus());
        iht.setAssignedTo(form.getAssignedTo().trim());
        iht.setLastUpdated(LocalDate.now());
    }

    private Specification<InheritanceCase> buildSpecification(InheritanceFilter filter) {
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
            if (filter.hasPlanningType()) {
                predicates.add(cb.equal(root.get("planningType"), filter.getPlanningType()));
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
                        cb.like(cb.lower(root.get("utr")), like)
                ));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
