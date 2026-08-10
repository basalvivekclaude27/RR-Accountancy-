package com.rraccountancy.app.controller;

import com.rraccountancy.app.domain.TaxReturn;
import com.rraccountancy.app.domain.TaxReturnStatus;
import com.rraccountancy.app.dto.TaxReturnFilter;
import com.rraccountancy.app.dto.TaxReturnForm;
import com.rraccountancy.app.service.TaxReturnService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/personal-income-tax")
public class TaxReturnController {

    private final TaxReturnService service;

    public TaxReturnController(TaxReturnService service) {
        this.service = service;
    }

    @GetMapping
    public String list(@ModelAttribute TaxReturnFilter filter, Model model) {
        Page<TaxReturn> page = service.search(filter);

        model.addAttribute("filter", filter);
        model.addAttribute("page", page);
        model.addAttribute("returns", page.getContent());
        model.addAttribute("clientTypes", service.allClientTypes());
        model.addAttribute("assignees", service.allAssignees());
        model.addAttribute("taxYears", service.allTaxYears());
        model.addAttribute("statuses", TaxReturnStatus.values());
        return "personal-income-tax/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        if (!model.containsAttribute("returnForm")) {
            model.addAttribute("returnForm", new TaxReturnForm());
        }
        model.addAttribute("statuses", TaxReturnStatus.values());
        model.addAttribute("formAction", "/personal-income-tax");
        model.addAttribute("formTitle", "New Tax Enquiry");
        return "personal-income-tax/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("returnForm") TaxReturnForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", TaxReturnStatus.values());
            model.addAttribute("formAction", "/personal-income-tax");
            model.addAttribute("formTitle", "New Tax Enquiry");
            return "personal-income-tax/form";
        }
        TaxReturn taxReturn = service.create(form);
        redirectAttributes.addFlashAttribute("successMessage", "Tax return created for " + taxReturn.getClientName() + ".");
        return "redirect:/personal-income-tax";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("taxReturn", service.get(id));
        return "personal-income-tax/view";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("returnForm")) {
            TaxReturn taxReturn = service.get(id);
            TaxReturnForm form = new TaxReturnForm();
            form.setClientName(taxReturn.getClientName());
            form.setUtr(taxReturn.getUtr());
            form.setClientType(taxReturn.getClientType());
            form.setTaxYearStart(taxReturn.getTaxYearStart());
            form.setStatus(taxReturn.getStatus());
            form.setRefundAmount(taxReturn.getRefundAmount());
            form.setAssignedTo(taxReturn.getAssignedTo());
            model.addAttribute("returnForm", form);
        }
        model.addAttribute("statuses", TaxReturnStatus.values());
        model.addAttribute("formAction", "/personal-income-tax/" + id + "/edit");
        model.addAttribute("formTitle", "Edit Tax Return");
        model.addAttribute("returnId", id);
        return "personal-income-tax/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                          @Valid @ModelAttribute("returnForm") TaxReturnForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", TaxReturnStatus.values());
            model.addAttribute("formAction", "/personal-income-tax/" + id + "/edit");
            model.addAttribute("formTitle", "Edit Tax Return");
            model.addAttribute("returnId", id);
            return "personal-income-tax/form";
        }
        TaxReturn taxReturn = service.update(id, form);
        redirectAttributes.addFlashAttribute("successMessage", "Tax return for " + taxReturn.getClientName() + " updated.");
        return "redirect:/personal-income-tax/" + id;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String notFound() {
        return "redirect:/personal-income-tax";
    }
}
