package com.rraccountancy.app.service;

import com.rraccountancy.app.domain.VatReturn;
import com.rraccountancy.app.dto.VatReturnFilter;
import com.rraccountancy.app.dto.VatReturnForm;
import com.rraccountancy.app.repository.VatReturnRepository;
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
public class VatReturnService {

    private static final int PAGE_SIZE = 6;

    private final VatReturnRepository repository;

    public VatReturnService(VatReturnRepository repository) {
        this.repository = repository;
    }

    public Page<VatReturn> search(VatReturnFilter filter) {
        Specification<VatReturn> spec = buildSpecification(filter);
        Sort sort = Sort.by(Sort.Direction.DESC, "periodStart").and(Sort.by(Sort.Direction.ASC, "id"));
        PageRequest pageRequest = PageRequest.of(filter.getPage(), PAGE_SIZE, sort);
        return repository.findAll(spec, pageRequest);
    }

    public List<String> allClientTypes() {
        return repository.findDistinctClientTypes();
    }

    public List<String> allSchemeTypes() {
        return repository.findDistinctSchemeTypes();
    }

    public List<LocalDate> allPeriods() {
        return repository.findDistinctPeriods();
    }

    public VatReturn get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("VAT return not found: " + id));
    }

    @Transactional
    public VatReturn create(VatReturnForm form) {
        VatReturn vatReturn = new VatReturn();
        applyForm(vatReturn, form);
        return repository.save(vatReturn);
    }

    @Transactional
    public VatReturn update(Long id, VatReturnForm form) {
        VatReturn vatReturn = get(id);
        applyForm(vatReturn, form);
        return repository.save(vatReturn);
    }

    private void applyForm(VatReturn vatReturn, VatReturnForm form) {
        vatReturn.setClientName(form.getClientName().trim());
        vatReturn.setVatNumber(form.getVatNumber().trim());
        vatReturn.setSchemeType(form.getSchemeType().trim());
        vatReturn.setClientType(form.getClientType().trim());
        vatReturn.setPeriodStart(form.getPeriodStart());
        vatReturn.setSalesExVat(form.getSalesExVat());
        vatReturn.setPurchasesExVat(form.getPurchasesExVat());
        vatReturn.setVatAmount(form.getVatAmount());
        vatReturn.setStatus(form.getStatus());
        vatReturn.setFiledOn(form.getFiledOn());
        vatReturn.setAssignedTo(form.getAssignedTo().trim());
    }

    private Specification<VatReturn> buildSpecification(VatReturnFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getPeriod() != null) {
                predicates.add(cb.equal(root.get("periodStart"), filter.getPeriod()));
            }
            if (filter.hasClientType()) {
                predicates.add(cb.equal(root.get("clientType"), filter.getClientType()));
            }
            if (filter.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), filter.getStatus()));
            }
            if (filter.hasSchemeType()) {
                predicates.add(cb.equal(root.get("schemeType"), filter.getSchemeType()));
            }
            if (filter.getDateFrom() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("periodStart"), filter.getDateFrom()));
            }
            if (filter.getDateTo() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("periodStart"), filter.getDateTo()));
            }
            if (filter.hasQuery()) {
                String like = "%" + filter.getQ().trim().toLowerCase(Locale.ROOT) + "%";
                predicates.add(cb.or(
                        cb.like(cb.lower(root.get("clientName")), like),
                        cb.like(cb.lower(root.get("vatNumber")), like)
                ));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
