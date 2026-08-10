package com.rraccountancy.app.controller;

import com.rraccountancy.app.domain.ServiceChargeAccount;
import com.rraccountancy.app.domain.ServiceChargeStatus;
import com.rraccountancy.app.dto.ServiceChargeFilter;
import com.rraccountancy.app.dto.ServiceChargeForm;
import com.rraccountancy.app.service.ServiceChargeAccountService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/service-charge")
public class ServiceChargeController {

    private final ServiceChargeAccountService service;

    public ServiceChargeController(ServiceChargeAccountService service) {
        this.service = service;
    }

    @GetMapping
    public String list(@ModelAttribute ServiceChargeFilter filter, Model model) {
        Page<ServiceChargeAccount> page = service.search(filter);

        model.addAttribute("filter", filter);
        model.addAttribute("page", page);
        model.addAttribute("accounts", page.getContent());
        model.addAttribute("propertyNames", service.allPropertyNames());
        model.addAttribute("financialYears", service.allFinancialYears());
        model.addAttribute("statuses", ServiceChargeStatus.values());
        return "service-charge/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        if (!model.containsAttribute("accountForm")) {
            model.addAttribute("accountForm", new ServiceChargeForm());
        }
        model.addAttribute("statuses", ServiceChargeStatus.values());
        model.addAttribute("formAction", "/service-charge");
        model.addAttribute("formTitle", "New Service Charge Account");
        return "service-charge/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("accountForm") ServiceChargeForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", ServiceChargeStatus.values());
            model.addAttribute("formAction", "/service-charge");
            model.addAttribute("formTitle", "New Service Charge Account");
            return "service-charge/form";
        }
        ServiceChargeAccount account = service.create(form);
        redirectAttributes.addFlashAttribute("successMessage", "Service charge account created for " + account.getPropertyName() + ".");
        return "redirect:/service-charge";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("account", service.get(id));
        return "service-charge/view";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("accountForm")) {
            ServiceChargeAccount account = service.get(id);
            ServiceChargeForm form = new ServiceChargeForm();
            form.setPropertyName(account.getPropertyName());
            form.setPropertyRef(account.getPropertyRef());
            form.setUnits(account.getUnits());
            form.setFinancialYearStart(account.getFinancialYearStart());
            form.setPeriod(java.time.YearMonth.from(account.getPeriodDate()));
            form.setBudgetedAmount(account.getBudgetedAmount());
            form.setCollectedAmount(account.getCollectedAmount());
            form.setStatus(account.getStatus());
            model.addAttribute("accountForm", form);
        }
        model.addAttribute("statuses", ServiceChargeStatus.values());
        model.addAttribute("formAction", "/service-charge/" + id + "/edit");
        model.addAttribute("formTitle", "Edit Service Charge Account");
        model.addAttribute("accountId", id);
        return "service-charge/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                          @Valid @ModelAttribute("accountForm") ServiceChargeForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", ServiceChargeStatus.values());
            model.addAttribute("formAction", "/service-charge/" + id + "/edit");
            model.addAttribute("formTitle", "Edit Service Charge Account");
            model.addAttribute("accountId", id);
            return "service-charge/form";
        }
        ServiceChargeAccount account = service.update(id, form);
        redirectAttributes.addFlashAttribute("successMessage", "Service charge account for " + account.getPropertyName() + " updated.");
        return "redirect:/service-charge/" + id;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String notFound() {
        return "redirect:/service-charge";
    }
}
