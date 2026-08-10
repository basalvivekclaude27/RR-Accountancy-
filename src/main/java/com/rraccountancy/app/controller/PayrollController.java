package com.rraccountancy.app.controller;

import com.rraccountancy.app.domain.PayrollFrequency;
import com.rraccountancy.app.domain.PayrollRun;
import com.rraccountancy.app.domain.PayrollStatus;
import com.rraccountancy.app.dto.PayrollRunFilter;
import com.rraccountancy.app.dto.PayrollRunForm;
import com.rraccountancy.app.service.PayrollRunService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/payroll")
public class PayrollController {

    private final PayrollRunService service;

    public PayrollController(PayrollRunService service) {
        this.service = service;
    }

    @GetMapping
    public String list(@ModelAttribute PayrollRunFilter filter, Model model) {
        Page<PayrollRun> page = service.search(filter);

        model.addAttribute("filter", filter);
        model.addAttribute("page", page);
        model.addAttribute("runs", page.getContent());
        model.addAttribute("clientNames", service.allClientNames());
        model.addAttribute("assignees", service.allAssignees());
        model.addAttribute("frequencies", PayrollFrequency.values());
        model.addAttribute("statuses", PayrollStatus.values());
        return "payroll/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        if (!model.containsAttribute("runForm")) {
            model.addAttribute("runForm", new PayrollRunForm());
        }
        model.addAttribute("frequencies", PayrollFrequency.values());
        model.addAttribute("statuses", PayrollStatus.values());
        model.addAttribute("formAction", "/payroll");
        model.addAttribute("formTitle", "New Payroll Run");
        return "payroll/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("runForm") PayrollRunForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("frequencies", PayrollFrequency.values());
            model.addAttribute("statuses", PayrollStatus.values());
            model.addAttribute("formAction", "/payroll");
            model.addAttribute("formTitle", "New Payroll Run");
            return "payroll/form";
        }
        PayrollRun run = service.create(form);
        redirectAttributes.addFlashAttribute("successMessage", "Payroll run created for " + run.getClientName() + " (" + run.getPayrollMonthLabel() + ").");
        return "redirect:/payroll";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("run", service.get(id));
        return "payroll/view";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("runForm")) {
            PayrollRun run = service.get(id);
            PayrollRunForm form = new PayrollRunForm();
            form.setClientName(run.getClientName());
            form.setPayrollMonth(java.time.YearMonth.from(run.getPayrollMonth()));
            form.setFrequency(run.getFrequency());
            form.setEmployees(run.getEmployees());
            form.setNetPay(run.getNetPay());
            form.setStatus(run.getStatus());
            form.setAssignedTo(run.getAssignedTo());
            model.addAttribute("runForm", form);
        }
        model.addAttribute("frequencies", PayrollFrequency.values());
        model.addAttribute("statuses", PayrollStatus.values());
        model.addAttribute("formAction", "/payroll/" + id + "/edit");
        model.addAttribute("formTitle", "Edit Payroll Run");
        model.addAttribute("runId", id);
        return "payroll/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                          @Valid @ModelAttribute("runForm") PayrollRunForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("frequencies", PayrollFrequency.values());
            model.addAttribute("statuses", PayrollStatus.values());
            model.addAttribute("formAction", "/payroll/" + id + "/edit");
            model.addAttribute("formTitle", "Edit Payroll Run");
            model.addAttribute("runId", id);
            return "payroll/form";
        }
        PayrollRun run = service.update(id, form);
        redirectAttributes.addFlashAttribute("successMessage", "Payroll run for " + run.getClientName() + " updated.");
        return "redirect:/payroll/" + id;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String notFound() {
        return "redirect:/payroll";
    }
}
