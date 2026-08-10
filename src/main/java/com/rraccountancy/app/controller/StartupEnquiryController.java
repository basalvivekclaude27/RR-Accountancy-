package com.rraccountancy.app.controller;

import com.rraccountancy.app.domain.*;
import com.rraccountancy.app.dto.StartupEnquiryFilter;
import com.rraccountancy.app.dto.StartupEnquiryForm;
import com.rraccountancy.app.service.StartupEnquiryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/business-startup")
public class StartupEnquiryController {

    private final StartupEnquiryService service;

    public StartupEnquiryController(StartupEnquiryService service) {
        this.service = service;
    }

    @GetMapping
    public String list(@ModelAttribute StartupEnquiryFilter filter, Model model) {
        Page<StartupEnquiry> page = service.search(filter);

        model.addAttribute("filter", filter);
        model.addAttribute("page", page);
        model.addAttribute("enquiries", page.getContent());
        model.addAttribute("businessTypes", service.allBusinessTypes());
        model.addAttribute("assignees", service.allAssignees());
        model.addAttribute("sources", EnquirySource.values());
        model.addAttribute("stages", StartupStage.values());
        model.addAttribute("statuses", StartupStatus.values());
        return "business-startup/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        if (!model.containsAttribute("enquiryForm")) {
            model.addAttribute("enquiryForm", new StartupEnquiryForm());
        }
        model.addAttribute("sources", EnquirySource.values());
        model.addAttribute("stages", StartupStage.values());
        model.addAttribute("statuses", StartupStatus.values());
        model.addAttribute("formAction", "/business-startup");
        model.addAttribute("formTitle", "New Startup Enquiry");
        return "business-startup/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("enquiryForm") StartupEnquiryForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("sources", EnquirySource.values());
            model.addAttribute("stages", StartupStage.values());
            model.addAttribute("statuses", StartupStatus.values());
            model.addAttribute("formAction", "/business-startup");
            model.addAttribute("formTitle", "New Startup Enquiry");
            return "business-startup/form";
        }
        StartupEnquiry enquiry = service.create(form);
        redirectAttributes.addFlashAttribute("successMessage", "Enquiry " + enquiry.getEnquiryRef() + " created for " + enquiry.getBusinessName() + ".");
        return "redirect:/business-startup";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("enquiry", service.get(id));
        return "business-startup/view";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("enquiryForm")) {
            StartupEnquiry enquiry = service.get(id);
            StartupEnquiryForm form = new StartupEnquiryForm();
            form.setClientName(enquiry.getClientName());
            form.setEnquiryRef(enquiry.getEnquiryRef());
            form.setBusinessName(enquiry.getBusinessName());
            form.setBusinessType(enquiry.getBusinessType());
            form.setStage(enquiry.getStage());
            form.setStatus(enquiry.getStatus());
            form.setSource(enquiry.getSource());
            form.setAssignedTo(enquiry.getAssignedTo());
            form.setEnquiryDate(enquiry.getEnquiryDate());
            model.addAttribute("enquiryForm", form);
        }
        model.addAttribute("sources", EnquirySource.values());
        model.addAttribute("stages", StartupStage.values());
        model.addAttribute("statuses", StartupStatus.values());
        model.addAttribute("formAction", "/business-startup/" + id + "/edit");
        model.addAttribute("formTitle", "Edit Startup Enquiry");
        model.addAttribute("enquiryId", id);
        return "business-startup/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                          @Valid @ModelAttribute("enquiryForm") StartupEnquiryForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("sources", EnquirySource.values());
            model.addAttribute("stages", StartupStage.values());
            model.addAttribute("statuses", StartupStatus.values());
            model.addAttribute("formAction", "/business-startup/" + id + "/edit");
            model.addAttribute("formTitle", "Edit Startup Enquiry");
            model.addAttribute("enquiryId", id);
            return "business-startup/form";
        }
        StartupEnquiry enquiry = service.update(id, form);
        redirectAttributes.addFlashAttribute("successMessage", "Enquiry " + enquiry.getEnquiryRef() + " updated.");
        return "redirect:/business-startup/" + id;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String notFound() {
        return "redirect:/business-startup";
    }
}
