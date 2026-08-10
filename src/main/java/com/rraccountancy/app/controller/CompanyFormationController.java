package com.rraccountancy.app.controller;

import com.rraccountancy.app.domain.*;
import com.rraccountancy.app.dto.CompanyFormationFilter;
import com.rraccountancy.app.dto.CompanyFormationForm;
import com.rraccountancy.app.service.CompanyFormationService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/company-formation")
public class CompanyFormationController {

    private final CompanyFormationService service;

    public CompanyFormationController(CompanyFormationService service) {
        this.service = service;
    }

    @GetMapping
    public String list(@ModelAttribute CompanyFormationFilter filter, Model model) {
        Page<CompanyFormationEnquiry> page = service.search(filter);

        model.addAttribute("filter", filter);
        model.addAttribute("page", page);
        model.addAttribute("enquiries", page.getContent());
        model.addAttribute("companyTypes", service.allCompanyTypes());
        model.addAttribute("jurisdictions", service.allJurisdictions());
        model.addAttribute("sources", EnquirySource.values());
        model.addAttribute("statuses", CompanyFormationStatus.values());
        return "company-formation/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        if (!model.containsAttribute("enquiryForm")) {
            model.addAttribute("enquiryForm", new CompanyFormationForm());
        }
        model.addAttribute("sources", EnquirySource.values());
        model.addAttribute("stages", CompanyFormationStage.values());
        model.addAttribute("statuses", CompanyFormationStatus.values());
        model.addAttribute("formAction", "/company-formation");
        model.addAttribute("formTitle", "New Formation Enquiry");
        return "company-formation/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("enquiryForm") CompanyFormationForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("sources", EnquirySource.values());
            model.addAttribute("stages", CompanyFormationStage.values());
            model.addAttribute("statuses", CompanyFormationStatus.values());
            model.addAttribute("formAction", "/company-formation");
            model.addAttribute("formTitle", "New Formation Enquiry");
            return "company-formation/form";
        }
        CompanyFormationEnquiry enquiry = service.create(form);
        redirectAttributes.addFlashAttribute("successMessage", "Enquiry " + enquiry.getEnquiryRef() + " created for " + enquiry.getCompanyName() + ".");
        return "redirect:/company-formation";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("enquiry", service.get(id));
        return "company-formation/view";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("enquiryForm")) {
            CompanyFormationEnquiry enquiry = service.get(id);
            CompanyFormationForm form = new CompanyFormationForm();
            form.setClientName(enquiry.getClientName());
            form.setEnquiryRef(enquiry.getEnquiryRef());
            form.setCompanyName(enquiry.getCompanyName());
            form.setCompanyType(enquiry.getCompanyType());
            form.setJurisdiction(enquiry.getJurisdiction());
            form.setStage(enquiry.getStage());
            form.setStatus(enquiry.getStatus());
            form.setSource(enquiry.getSource());
            form.setAssignedTo(enquiry.getAssignedTo());
            form.setEnquiryDate(enquiry.getEnquiryDate());
            model.addAttribute("enquiryForm", form);
        }
        model.addAttribute("sources", EnquirySource.values());
        model.addAttribute("stages", CompanyFormationStage.values());
        model.addAttribute("statuses", CompanyFormationStatus.values());
        model.addAttribute("formAction", "/company-formation/" + id + "/edit");
        model.addAttribute("formTitle", "Edit Formation Enquiry");
        model.addAttribute("enquiryId", id);
        return "company-formation/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                          @Valid @ModelAttribute("enquiryForm") CompanyFormationForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("sources", EnquirySource.values());
            model.addAttribute("stages", CompanyFormationStage.values());
            model.addAttribute("statuses", CompanyFormationStatus.values());
            model.addAttribute("formAction", "/company-formation/" + id + "/edit");
            model.addAttribute("formTitle", "Edit Formation Enquiry");
            model.addAttribute("enquiryId", id);
            return "company-formation/form";
        }
        CompanyFormationEnquiry enquiry = service.update(id, form);
        redirectAttributes.addFlashAttribute("successMessage", "Enquiry " + enquiry.getEnquiryRef() + " updated.");
        return "redirect:/company-formation/" + id;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String notFound() {
        return "redirect:/company-formation";
    }
}
