package com.rraccountancy.app.service;

import com.rraccountancy.app.domain.BusinessTaxReturn;
import com.rraccountancy.app.dto.BusinessTaxReturnFilter;
import com.rraccountancy.app.dto.BusinessTaxReturnForm;
import com.rraccountancy.app.repository.BusinessTaxReturnRepository;
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
public class BusinessTaxReturnService {

    private static final int PAGE_SIZE = 6;

    private final BusinessTaxReturnRepository repository;

    public BusinessTaxReturnService(BusinessTaxReturnRepository repository) {
        this.repository = repository;
    }

    public Page<BusinessTaxReturn> search(BusinessTaxReturnFilter filter) {
        Specification<BusinessTaxReturn> spec = buildSpecification(filter);
        PageRequest pageRequest = PageRequest.of(filter.getPage(), PAGE_SIZE, Sort.by(Sort.Direction.DESC, "lastUpdated"));
        return repository.findAll(spec, pageRequest);
    }

    public List<String> allBusinessTypes() {
        return repository.findDistinctBusinessTypes();
    }

    public List<String> allIndustries() {
        return repository.findDistinctIndustries();
    }

    public List<Integer> allTaxYears() {
        return repository.findDistinctTaxYears();
    }

    public BusinessTaxReturn get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Business tax return not found: " + id));
    }

    @Transactional
    public BusinessTaxReturn create(BusinessTaxReturnForm form) {
        BusinessTaxReturn taxReturn = new BusinessTaxReturn();
        applyForm(taxReturn, form);
        return repository.save(taxReturn);
    }

    @Transactional
    public BusinessTaxReturn update(Long id, BusinessTaxReturnForm form) {
        BusinessTaxReturn taxReturn = get(id);
        applyForm(taxReturn, form);
        return repository.save(taxReturn);
    }

    private void applyForm(BusinessTaxReturn taxReturn, BusinessTaxReturnForm form) {
        taxReturn.setClientName(form.getClientName().trim());
        taxReturn.setUtr(form.getUtr().trim());
        taxReturn.setBusinessType(form.getBusinessType().trim());
        taxReturn.setIndustry(form.getIndustry().trim());
        taxReturn.setTaxYearStart(form.getTaxYearStart());
        taxReturn.setProfitBeforeTax(form.getProfitBeforeTax());
        taxReturn.setTaxPayable(form.getTaxPayable());
        taxReturn.setStatus(form.getStatus());
        taxReturn.setAssignedTo(form.getAssignedTo().trim());
    }

    private Specification<BusinessTaxReturn> buildSpecification(BusinessTaxReturnFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getTaxYear() != null) {
                predicates.add(cb.equal(root.get("taxYearStart"), filter.getTaxYear()));
            }
            if (filter.hasBusinessType()) {
                predicates.add(cb.equal(root.get("businessType"), filter.getBusinessType()));
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
                        cb.like(root.get("utr"), filter.getQ().trim())
                ));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
