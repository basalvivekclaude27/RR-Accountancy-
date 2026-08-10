package com.rraccountancy.app.service;

import com.rraccountancy.app.domain.CapitalGainReturn;
import com.rraccountancy.app.dto.CapitalGainFilter;
import com.rraccountancy.app.dto.CapitalGainForm;
import com.rraccountancy.app.repository.CapitalGainReturnRepository;
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
public class CapitalGainService {

    private static final int PAGE_SIZE = 6;

    private final CapitalGainReturnRepository repository;

    public CapitalGainService(CapitalGainReturnRepository repository) {
        this.repository = repository;
    }

    public Page<CapitalGainReturn> search(CapitalGainFilter filter) {
        Specification<CapitalGainReturn> spec = buildSpecification(filter);
        Sort sort = Sort.by(Sort.Direction.DESC, "lastUpdated").and(Sort.by(Sort.Direction.ASC, "id"));
        PageRequest pageRequest = PageRequest.of(filter.getPage(), PAGE_SIZE, sort);
        return repository.findAll(spec, pageRequest);
    }

    public List<String> allClientTypes() {
        return repository.findDistinctClientTypes();
    }

    public List<String> allAssetTypes() {
        return repository.findDistinctAssetTypes();
    }

    public List<Integer> allTaxYears() {
        return repository.findDistinctTaxYears();
    }

    public CapitalGainReturn get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("CGT case not found: " + id));
    }

    @Transactional
    public CapitalGainReturn create(CapitalGainForm form) {
        CapitalGainReturn cgt = new CapitalGainReturn();
        applyForm(cgt, form);
        return repository.save(cgt);
    }

    @Transactional
    public CapitalGainReturn update(Long id, CapitalGainForm form) {
        CapitalGainReturn cgt = get(id);
        applyForm(cgt, form);
        return repository.save(cgt);
    }

    private void applyForm(CapitalGainReturn cgt, CapitalGainForm form) {
        cgt.setClientName(form.getClientName().trim());
        cgt.setUtr(form.getUtr().trim());
        cgt.setClientType(form.getClientType().trim());
        cgt.setAssetType(form.getAssetType().trim());
        cgt.setTaxYearStart(form.getTaxYearStart());
        cgt.setDateOfDisposal(form.getDateOfDisposal());
        cgt.setGainOrLoss(form.getGainOrLoss());
        cgt.setTaxPayable(form.getTaxPayable());
        cgt.setStatus(form.getStatus());
        cgt.setAssignedTo(form.getAssignedTo().trim());
        cgt.setLastUpdated(java.time.LocalDate.now());
    }

    private Specification<CapitalGainReturn> buildSpecification(CapitalGainFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getTaxYear() != null) {
                predicates.add(cb.equal(root.get("taxYearStart"), filter.getTaxYear()));
            }
            if (filter.hasClientType()) {
                predicates.add(cb.equal(root.get("clientType"), filter.getClientType()));
            }
            if (filter.hasAssetType()) {
                predicates.add(cb.equal(root.get("assetType"), filter.getAssetType()));
            }
            if (filter.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), filter.getStatus()));
            }
            if (filter.getDateFrom() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dateOfDisposal"), filter.getDateFrom()));
            }
            if (filter.getDateTo() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dateOfDisposal"), filter.getDateTo()));
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
