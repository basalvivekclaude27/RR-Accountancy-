package com.rraccountancy.app.controller;

import com.rraccountancy.app.domain.CorporateTaxReturn;
import com.rraccountancy.app.domain.TaxReturnStatus;
import com.rraccountancy.app.dto.CorporateTaxFilter;
import com.rraccountancy.app.dto.CorporateTaxForm;
import com.rraccountancy.app.service.CorporateTaxService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/corporation-tax")
public class CorporateTaxController {

    /** Corporation tax returns never reach REFUND_ISSUED — that status doesn't apply here. */
    private static final TaxReturnStatus[] STATUSES = {
            TaxReturnStatus.PROCESSING, TaxReturnStatus.DOCUMENTS_PENDING,
            TaxReturnStatus.IN_REVIEW, TaxReturnStatus.FILED
    };

    private final CorporateTaxService service;

    public CorporateTaxController(CorporateTaxService service) {
        this.service = service;
    }

    @GetMapping
    public String list(@ModelAttribute CorporateTaxFilter filter, Model model) {
        Page<CorporateTaxReturn> page = service.search(filter);

        model.addAttribute("filter", filter);
        model.addAttribute("page", page);
        model.addAttribute("returns", page.getContent());
        model.addAttribute("clientTypes", service.allClientTypes());
        model.addAttribute("industries", service.allIndustries());
        model.addAttribute("statuses", STATUSES);
        return "corporation-tax/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        if (!model.containsAttribute("returnForm")) {
            model.addAttribute("returnForm", new CorporateTaxForm());
        }
        model.addAttribute("statuses", STATUSES);
        model.addAttribute("formAction", "/corporation-tax");
        model.addAttribute("formTitle", "New Corporate Tax Enquiry");
        return "corporation-tax/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("returnForm") CorporateTaxForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", STATUSES);
            model.addAttribute("formAction", "/corporation-tax");
            model.addAttribute("formTitle", "New Corporate Tax Enquiry");
            return "corporation-tax/form";
        }
        CorporateTaxReturn taxReturn = service.create(form);
        redirectAttributes.addFlashAttribute("successMessage", "Corporate tax return created for " + taxReturn.getClientName() + ".");
        return "redirect:/corporation-tax";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("taxReturn", service.get(id));
        return "corporation-tax/view";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("returnForm")) {
            CorporateTaxReturn taxReturn = service.get(id);
            CorporateTaxForm form = new CorporateTaxForm();
            form.setClientName(taxReturn.getClientName());
            form.setUtr(taxReturn.getUtr());
            form.setClientType(taxReturn.getClientType());
            form.setIndustry(taxReturn.getIndustry());
            form.setAccountingPeriodStart(taxReturn.getAccountingPeriodStart());
            form.setAccountingPeriodEnd(taxReturn.getAccountingPeriodEnd());
            form.setTaxableProfit(taxReturn.getTaxableProfit());
            form.setTaxPayable(taxReturn.getTaxPayable());
            form.setStatus(taxReturn.getStatus());
            form.setDueDate(taxReturn.getDueDate());
            form.setAssignedTo(taxReturn.getAssignedTo());
            model.addAttribute("returnForm", form);
        }
        model.addAttribute("statuses", STATUSES);
        model.addAttribute("formAction", "/corporation-tax/" + id + "/edit");
        model.addAttribute("formTitle", "Edit Corporate Tax Return");
        model.addAttribute("returnId", id);
        return "corporation-tax/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                          @Valid @ModelAttribute("returnForm") CorporateTaxForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", STATUSES);
            model.addAttribute("formAction", "/corporation-tax/" + id + "/edit");
            model.addAttribute("formTitle", "Edit Corporate Tax Return");
            model.addAttribute("returnId", id);
            return "corporation-tax/form";
        }
        CorporateTaxReturn taxReturn = service.update(id, form);
        redirectAttributes.addFlashAttribute("successMessage", "Corporate tax return for " + taxReturn.getClientName() + " updated.");
        return "redirect:/corporation-tax/" + id;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String notFound() {
        return "redirect:/corporation-tax";
    }
}
