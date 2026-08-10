package com.rraccountancy.app.controller;

import com.rraccountancy.app.domain.BusinessTaxReturn;
import com.rraccountancy.app.domain.TaxReturnStatus;
import com.rraccountancy.app.dto.BusinessTaxReturnFilter;
import com.rraccountancy.app.dto.BusinessTaxReturnForm;
import com.rraccountancy.app.service.BusinessTaxReturnService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/business-income-tax")
public class BusinessTaxReturnController {

    private final BusinessTaxReturnService service;

    public BusinessTaxReturnController(BusinessTaxReturnService service) {
        this.service = service;
    }

    @GetMapping
    public String list(@ModelAttribute BusinessTaxReturnFilter filter, Model model) {
        Page<BusinessTaxReturn> page = service.search(filter);

        model.addAttribute("filter", filter);
        model.addAttribute("page", page);
        model.addAttribute("returns", page.getContent());
        model.addAttribute("businessTypes", service.allBusinessTypes());
        model.addAttribute("industries", service.allIndustries());
        model.addAttribute("taxYears", service.allTaxYears());
        model.addAttribute("statuses", TaxReturnStatus.values());
        return "business-income-tax/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        if (!model.containsAttribute("returnForm")) {
            model.addAttribute("returnForm", new BusinessTaxReturnForm());
        }
        model.addAttribute("statuses", TaxReturnStatus.values());
        model.addAttribute("formAction", "/business-income-tax");
        model.addAttribute("formTitle", "New Tax Enquiry");
        return "business-income-tax/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("returnForm") BusinessTaxReturnForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", TaxReturnStatus.values());
            model.addAttribute("formAction", "/business-income-tax");
            model.addAttribute("formTitle", "New Tax Enquiry");
            return "business-income-tax/form";
        }
        BusinessTaxReturn taxReturn = service.create(form);
        redirectAttributes.addFlashAttribute("successMessage", "Tax return created for " + taxReturn.getClientName() + ".");
        return "redirect:/business-income-tax";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("taxReturn", service.get(id));
        return "business-income-tax/view";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("returnForm")) {
            BusinessTaxReturn taxReturn = service.get(id);
            BusinessTaxReturnForm form = new BusinessTaxReturnForm();
            form.setClientName(taxReturn.getClientName());
            form.setUtr(taxReturn.getUtr());
            form.setBusinessType(taxReturn.getBusinessType());
            form.setIndustry(taxReturn.getIndustry());
            form.setTaxYearStart(taxReturn.getTaxYearStart());
            form.setProfitBeforeTax(taxReturn.getProfitBeforeTax());
            form.setTaxPayable(taxReturn.getTaxPayable());
            form.setStatus(taxReturn.getStatus());
            form.setAssignedTo(taxReturn.getAssignedTo());
            model.addAttribute("returnForm", form);
        }
        model.addAttribute("statuses", TaxReturnStatus.values());
        model.addAttribute("formAction", "/business-income-tax/" + id + "/edit");
        model.addAttribute("formTitle", "Edit Tax Return");
        model.addAttribute("returnId", id);
        return "business-income-tax/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                          @Valid @ModelAttribute("returnForm") BusinessTaxReturnForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", TaxReturnStatus.values());
            model.addAttribute("formAction", "/business-income-tax/" + id + "/edit");
            model.addAttribute("formTitle", "Edit Tax Return");
            model.addAttribute("returnId", id);
            return "business-income-tax/form";
        }
        BusinessTaxReturn taxReturn = service.update(id, form);
        redirectAttributes.addFlashAttribute("successMessage", "Tax return for " + taxReturn.getClientName() + " updated.");
        return "redirect:/business-income-tax/" + id;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String notFound() {
        return "redirect:/business-income-tax";
    }
}
