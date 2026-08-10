package com.rraccountancy.app.service;

import com.rraccountancy.app.domain.BusinessPlan;
import com.rraccountancy.app.dto.BusinessPlanFilter;
import com.rraccountancy.app.dto.BusinessPlanForm;
import com.rraccountancy.app.repository.BusinessPlanRepository;
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
public class BusinessPlanService {

    private static final int PAGE_SIZE = 6;

    private final BusinessPlanRepository repository;

    public BusinessPlanService(BusinessPlanRepository repository) {
        this.repository = repository;
    }

    public Page<BusinessPlan> search(BusinessPlanFilter filter) {
        Specification<BusinessPlan> spec = buildSpecification(filter);
        PageRequest pageRequest = PageRequest.of(filter.getPage(), PAGE_SIZE, Sort.by(Sort.Direction.DESC, "lastUpdated"));
        return repository.findAll(spec, pageRequest);
    }

    public List<String> allClientNames() {
        return repository.findDistinctClientNames();
    }

    public List<String> allPlanTypes() {
        return repository.findDistinctPlanTypes();
    }

    public List<String> allIndustries() {
        return repository.findDistinctIndustries();
    }

    public BusinessPlan get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Business plan not found: " + id));
    }

    @Transactional
    public BusinessPlan create(BusinessPlanForm form) {
        BusinessPlan plan = new BusinessPlan();
        plan.setPlanRef(nextPlanRef());
        applyForm(plan, form);
        return repository.save(plan);
    }

    @Transactional
    public BusinessPlan update(Long id, BusinessPlanForm form) {
        BusinessPlan plan = get(id);
        applyForm(plan, form);
        return repository.save(plan);
    }

    private void applyForm(BusinessPlan plan, BusinessPlanForm form) {
        plan.setClientName(form.getClientName().trim());
        if (form.getPlanRef() != null && !form.getPlanRef().isBlank()) {
            plan.setPlanRef(form.getPlanRef().trim());
        }
        plan.setPlanName(form.getPlanName().trim());
        plan.setPlanType(form.getPlanType().trim());
        plan.setIndustry(form.getIndustry().trim());
        plan.setStatus(form.getStatus());
        plan.setRevenuePotential(form.getRevenuePotential());
    }

    private String nextPlanRef() {
        long count = repository.count();
        return String.format(Locale.ROOT, "BP-%04d", 1007 + count);
    }

    private Specification<BusinessPlan> buildSpecification(BusinessPlanFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.hasClient()) {
                predicates.add(cb.equal(root.get("clientName"), filter.getClient()));
            }
            if (filter.hasPlanType()) {
                predicates.add(cb.equal(root.get("planType"), filter.getPlanType()));
            }
            if (filter.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), filter.getStatus()));
            }
            if (filter.hasIndustry()) {
                predicates.add(cb.equal(root.get("industry"), filter.getIndustry()));
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
                        cb.like(cb.lower(root.get("planName")), like)
                ));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
