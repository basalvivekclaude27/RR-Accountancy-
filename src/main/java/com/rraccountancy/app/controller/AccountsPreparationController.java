package com.rraccountancy.app.controller;

import com.rraccountancy.app.domain.AccountStatus;
import com.rraccountancy.app.domain.AccountType;
import com.rraccountancy.app.domain.AccountsPreparationJob;
import com.rraccountancy.app.dto.AccountsPreparationJobFilter;
import com.rraccountancy.app.dto.AccountsPreparationJobForm;
import com.rraccountancy.app.service.AccountsPreparationJobService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/accounts-preparation")
public class AccountsPreparationController {

    private final AccountsPreparationJobService service;

    public AccountsPreparationController(AccountsPreparationJobService service) {
        this.service = service;
    }

    @GetMapping
    public String list(@ModelAttribute AccountsPreparationJobFilter filter, Model model) {
        Page<AccountsPreparationJob> page = service.search(filter);

        model.addAttribute("filter", filter);
        model.addAttribute("page", page);
        model.addAttribute("jobs", page.getContent());
        model.addAttribute("clientNames", service.allClientNames());
        model.addAttribute("assignees", service.allAssignees());
        model.addAttribute("financialYears", service.allFinancialYears());
        model.addAttribute("accountTypes", AccountType.values());
        model.addAttribute("statuses", AccountStatus.values());
        return "accounts-preparation/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        if (!model.containsAttribute("jobForm")) {
            model.addAttribute("jobForm", new AccountsPreparationJobForm());
        }
        model.addAttribute("accountTypes", AccountType.values());
        model.addAttribute("statuses", AccountStatus.values());
        model.addAttribute("formAction", "/accounts-preparation");
        model.addAttribute("formTitle", "New Accounts Preparation Job");
        return "accounts-preparation/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("jobForm") AccountsPreparationJobForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("accountTypes", AccountType.values());
            model.addAttribute("statuses", AccountStatus.values());
            model.addAttribute("formAction", "/accounts-preparation");
            model.addAttribute("formTitle", "New Accounts Preparation Job");
            return "accounts-preparation/form";
        }
        AccountsPreparationJob job = service.create(form);
        redirectAttributes.addFlashAttribute("successMessage", "Job " + job.getJobRef() + " created for " + job.getClientName() + ".");
        return "redirect:/accounts-preparation";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("job", service.get(id));
        return "accounts-preparation/view";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("jobForm")) {
            AccountsPreparationJob job = service.get(id);
            AccountsPreparationJobForm form = new AccountsPreparationJobForm();
            form.setClientName(job.getClientName());
            form.setJobRef(job.getJobRef());
            form.setFinancialYearStart(job.getFinancialYearStart());
            form.setAccountType(job.getAccountType());
            form.setStatus(job.getStatus());
            form.setAssignedTo(job.getAssignedTo());
            model.addAttribute("jobForm", form);
        }
        model.addAttribute("accountTypes", AccountType.values());
        model.addAttribute("statuses", AccountStatus.values());
        model.addAttribute("formAction", "/accounts-preparation/" + id + "/edit");
        model.addAttribute("formTitle", "Edit Accounts Preparation Job");
        model.addAttribute("jobId", id);
        return "accounts-preparation/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                          @Valid @ModelAttribute("jobForm") AccountsPreparationJobForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("accountTypes", AccountType.values());
            model.addAttribute("statuses", AccountStatus.values());
            model.addAttribute("formAction", "/accounts-preparation/" + id + "/edit");
            model.addAttribute("formTitle", "Edit Accounts Preparation Job");
            model.addAttribute("jobId", id);
            return "accounts-preparation/form";
        }
        AccountsPreparationJob job = service.update(id, form);
        redirectAttributes.addFlashAttribute("successMessage", "Job " + job.getJobRef() + " updated.");
        return "redirect:/accounts-preparation/" + id;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String notFound() {
        return "redirect:/accounts-preparation";
    }
}
