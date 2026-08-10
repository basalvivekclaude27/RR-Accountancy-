package com.rraccountancy.app.service;

import com.rraccountancy.app.domain.CorporateTaxReturn;
import com.rraccountancy.app.dto.CorporateTaxFilter;
import com.rraccountancy.app.dto.CorporateTaxForm;
import com.rraccountancy.app.repository.CorporateTaxReturnRepository;
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
public class CorporateTaxService {

    private static final int PAGE_SIZE = 6;

    private final CorporateTaxReturnRepository repository;

    public CorporateTaxService(CorporateTaxReturnRepository repository) {
        this.repository = repository;
    }

    public Page<CorporateTaxReturn> search(CorporateTaxFilter filter) {
        Specification<CorporateTaxReturn> spec = buildSpecification(filter);
        PageRequest pageRequest = PageRequest.of(filter.getPage(), PAGE_SIZE, Sort.by(Sort.Direction.ASC, "dueDate"));
        return repository.findAll(spec, pageRequest);
    }

    public List<String> allClientTypes() {
        return repository.findDistinctClientTypes();
    }

    public List<String> allIndustries() {
        return repository.findDistinctIndustries();
    }

    public CorporateTaxReturn get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Corporate tax return not found: " + id));
    }

    @Transactional
    public CorporateTaxReturn create(CorporateTaxForm form) {
        CorporateTaxReturn taxReturn = new CorporateTaxReturn();
        applyForm(taxReturn, form);
        return repository.save(taxReturn);
    }

    @Transactional
    public CorporateTaxReturn update(Long id, CorporateTaxForm form) {
        CorporateTaxReturn taxReturn = get(id);
        applyForm(taxReturn, form);
        return repository.save(taxReturn);
    }

    private void applyForm(CorporateTaxReturn taxReturn, CorporateTaxForm form) {
        taxReturn.setClientName(form.getClientName().trim());
        taxReturn.setUtr(form.getUtr().trim());
        taxReturn.setClientType(form.getClientType().trim());
        taxReturn.setIndustry(form.getIndustry().trim());
        taxReturn.setAccountingPeriodStart(form.getAccountingPeriodStart());
        taxReturn.setAccountingPeriodEnd(form.getAccountingPeriodEnd());
        taxReturn.setTaxableProfit(form.getTaxableProfit());
        taxReturn.setTaxPayable(form.getTaxPayable());
        taxReturn.setStatus(form.getStatus());
        taxReturn.setDueDate(form.getDueDate());
        taxReturn.setAssignedTo(form.getAssignedTo().trim());
    }

    private Specification<CorporateTaxReturn> buildSpecification(CorporateTaxFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.hasClientType()) {
                predicates.add(cb.equal(root.get("clientType"), filter.getClientType()));
            }
            if (filter.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), filter.getStatus()));
            }
            if (filter.hasIndustry()) {
                predicates.add(cb.equal(root.get("industry"), filter.getIndustry()));
            }
            if (filter.getDueFrom() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dueDate"), filter.getDueFrom()));
            }
            if (filter.getDueTo() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dueDate"), filter.getDueTo()));
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
