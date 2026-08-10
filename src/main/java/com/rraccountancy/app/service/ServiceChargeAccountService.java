package com.rraccountancy.app.service;

import com.rraccountancy.app.domain.ServiceChargeAccount;
import com.rraccountancy.app.dto.ServiceChargeFilter;
import com.rraccountancy.app.dto.ServiceChargeForm;
import com.rraccountancy.app.repository.ServiceChargeAccountRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

@Service
public class ServiceChargeAccountService {

    private static final int PAGE_SIZE = 6;

    private final ServiceChargeAccountRepository repository;

    public ServiceChargeAccountService(ServiceChargeAccountRepository repository) {
        this.repository = repository;
    }

    public Page<ServiceChargeAccount> search(ServiceChargeFilter filter) {
        Specification<ServiceChargeAccount> spec = buildSpecification(filter);
        PageRequest pageRequest = PageRequest.of(filter.getPage(), PAGE_SIZE, Sort.by(Sort.Direction.DESC, "lastUpdated"));
        return repository.findAll(spec, pageRequest);
    }

    public List<String> allPropertyNames() {
        return repository.findDistinctPropertyNames();
    }

    public List<Integer> allFinancialYears() {
        return repository.findDistinctFinancialYears();
    }

    public ServiceChargeAccount get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Service charge account not found: " + id));
    }

    @Transactional
    public ServiceChargeAccount create(ServiceChargeForm form) {
        ServiceChargeAccount account = new ServiceChargeAccount();
        account.setPropertyRef(nextPropertyRef());
        applyForm(account, form);
        return repository.save(account);
    }

    @Transactional
    public ServiceChargeAccount update(Long id, ServiceChargeForm form) {
        ServiceChargeAccount account = get(id);
        applyForm(account, form);
        return repository.save(account);
    }

    private void applyForm(ServiceChargeAccount account, ServiceChargeForm form) {
        account.setPropertyName(form.getPropertyName().trim());
        if (form.getPropertyRef() != null && !form.getPropertyRef().isBlank()) {
            account.setPropertyRef(form.getPropertyRef().trim());
        }
        account.setUnits(form.getUnits());
        account.setFinancialYearStart(form.getFinancialYearStart());
        account.setPeriodDate(form.getPeriod().atDay(1));
        account.setBudgetedAmount(form.getBudgetedAmount());
        account.setCollectedAmount(form.getCollectedAmount());
        account.setStatus(form.getStatus());
    }

    private String nextPropertyRef() {
        long count = repository.count();
        return String.format(Locale.ROOT, "PR-%04d", 1007 + count);
    }

    private Specification<ServiceChargeAccount> buildSpecification(ServiceChargeFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.hasProperty()) {
                predicates.add(cb.equal(root.get("propertyName"), filter.getProperty()));
            }
            if (filter.getFinancialYear() != null) {
                predicates.add(cb.equal(root.get("financialYearStart"), filter.getFinancialYear()));
            }
            if (filter.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), filter.getStatus()));
            }
            if (filter.getCollectionStatus() != null) {
                predicates.add(cb.equal(root.get("status"), filter.getCollectionStatus()));
            }
            if (filter.getPeriod() != null) {
                YearMonth ym = filter.getPeriod();
                predicates.add(cb.between(root.get("periodDate"), ym.atDay(1), ym.atEndOfMonth()));
            }
            if (filter.hasQuery()) {
                String like = "%" + filter.getQ().trim().toLowerCase(Locale.ROOT) + "%";
                predicates.add(cb.or(
                        cb.like(cb.lower(root.get("propertyName")), like),
                        cb.like(cb.lower(root.get("propertyRef")), like)
                ));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
