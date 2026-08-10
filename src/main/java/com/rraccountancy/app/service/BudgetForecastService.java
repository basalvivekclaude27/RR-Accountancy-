package com.rraccountancy.app.service;

import com.rraccountancy.app.domain.BudgetForecast;
import com.rraccountancy.app.dto.BudgetForecastFilter;
import com.rraccountancy.app.dto.BudgetForecastForm;
import com.rraccountancy.app.repository.BudgetForecastRepository;
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
public class BudgetForecastService {

    private static final int PAGE_SIZE = 6;

    private final BudgetForecastRepository repository;

    public BudgetForecastService(BudgetForecastRepository repository) {
        this.repository = repository;
    }

    public Page<BudgetForecast> search(BudgetForecastFilter filter) {
        Specification<BudgetForecast> spec = buildSpecification(filter);
        PageRequest pageRequest = PageRequest.of(filter.getPage(), PAGE_SIZE, Sort.by(Sort.Direction.DESC, "lastUpdated"));
        return repository.findAll(spec, pageRequest);
    }

    public List<String> allClientNames() {
        return repository.findDistinctClientNames();
    }

    public List<Integer> allFinancialYears() {
        return repository.findDistinctFinancialYears();
    }

    public BudgetForecast get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Budget/forecast not found: " + id));
    }

    @Transactional
    public BudgetForecast create(BudgetForecastForm form) {
        BudgetForecast b = new BudgetForecast();
        applyForm(b, form);
        return repository.save(b);
    }

    @Transactional
    public BudgetForecast update(Long id, BudgetForecastForm form) {
        BudgetForecast b = get(id);
        applyForm(b, form);
        return repository.save(b);
    }

    private void applyForm(BudgetForecast b, BudgetForecastForm form) {
        b.setClientName(form.getClientName().trim());
        b.setType(form.getType());
        b.setFinancialYearStart(form.getFinancialYearStart());
        b.setPeriod(form.getPeriod());
        b.setBudgetedAmount(form.getBudgetedAmount());
        b.setStatus(form.getStatus());
    }

    private Specification<BudgetForecast> buildSpecification(BudgetForecastFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.hasClient()) {
                predicates.add(cb.equal(root.get("clientName"), filter.getClient()));
            }
            if (filter.getType() != null) {
                predicates.add(cb.equal(root.get("type"), filter.getType()));
            }
            if (filter.getFinancialYear() != null) {
                predicates.add(cb.equal(root.get("financialYearStart"), filter.getFinancialYear()));
            }
            if (filter.getPeriod() != null) {
                predicates.add(cb.equal(root.get("period"), filter.getPeriod()));
            }
            if (filter.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), filter.getStatus()));
            }
            if (filter.hasQuery()) {
                String like = "%" + filter.getQ().trim().toLowerCase(Locale.ROOT) + "%";
                predicates.add(cb.like(cb.lower(root.get("clientName")), like));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
