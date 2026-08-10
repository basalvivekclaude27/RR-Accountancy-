package com.rraccountancy.app.controller;

import com.rraccountancy.app.domain.BusinessPlan;
import com.rraccountancy.app.domain.BusinessPlanStatus;
import com.rraccountancy.app.dto.BusinessPlanFilter;
import com.rraccountancy.app.dto.BusinessPlanForm;
import com.rraccountancy.app.service.BusinessPlanService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/business-plans")
public class BusinessPlanController {

    private final BusinessPlanService service;

    public BusinessPlanController(BusinessPlanService service) {
        this.service = service;
    }

    @GetMapping
    public String list(@ModelAttribute BusinessPlanFilter filter, Model model) {
        Page<BusinessPlan> page = service.search(filter);

        model.addAttribute("filter", filter);
        model.addAttribute("page", page);
        model.addAttribute("plans", page.getContent());
        model.addAttribute("clientNames", service.allClientNames());
        model.addAttribute("planTypes", service.allPlanTypes());
        model.addAttribute("industries", service.allIndustries());
        model.addAttribute("statuses", BusinessPlanStatus.values());
        return "business-plans/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        if (!model.containsAttribute("planForm")) {
            model.addAttribute("planForm", new BusinessPlanForm());
        }
        model.addAttribute("statuses", BusinessPlanStatus.values());
        model.addAttribute("formAction", "/business-plans");
        model.addAttribute("formTitle", "New Business Plan");
        return "business-plans/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("planForm") BusinessPlanForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", BusinessPlanStatus.values());
            model.addAttribute("formAction", "/business-plans");
            model.addAttribute("formTitle", "New Business Plan");
            return "business-plans/form";
        }
        BusinessPlan plan = service.create(form);
        redirectAttributes.addFlashAttribute("successMessage", "Plan " + plan.getPlanRef() + " created for " + plan.getClientName() + ".");
        return "redirect:/business-plans";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("plan", service.get(id));
        return "business-plans/view";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("planForm")) {
            BusinessPlan plan = service.get(id);
            BusinessPlanForm form = new BusinessPlanForm();
            form.setClientName(plan.getClientName());
            form.setPlanRef(plan.getPlanRef());
            form.setPlanName(plan.getPlanName());
            form.setPlanType(plan.getPlanType());
            form.setIndustry(plan.getIndustry());
            form.setStatus(plan.getStatus());
            form.setRevenuePotential(plan.getRevenuePotential());
            model.addAttribute("planForm", form);
        }
        model.addAttribute("statuses", BusinessPlanStatus.values());
        model.addAttribute("formAction", "/business-plans/" + id + "/edit");
        model.addAttribute("formTitle", "Edit Business Plan");
        model.addAttribute("planId", id);
        return "business-plans/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                          @Valid @ModelAttribute("planForm") BusinessPlanForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", BusinessPlanStatus.values());
            model.addAttribute("formAction", "/business-plans/" + id + "/edit");
            model.addAttribute("formTitle", "Edit Business Plan");
            model.addAttribute("planId", id);
            return "business-plans/form";
        }
        BusinessPlan plan = service.update(id, form);
        redirectAttributes.addFlashAttribute("successMessage", "Plan " + plan.getPlanRef() + " updated.");
        return "redirect:/business-plans/" + id;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String notFound() {
        return "redirect:/business-plans";
    }
}
