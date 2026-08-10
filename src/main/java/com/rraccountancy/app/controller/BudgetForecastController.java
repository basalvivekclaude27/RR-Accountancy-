package com.rraccountancy.app.controller;

import com.rraccountancy.app.domain.*;
import com.rraccountancy.app.dto.BudgetForecastFilter;
import com.rraccountancy.app.dto.BudgetForecastForm;
import com.rraccountancy.app.service.BudgetForecastService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/budgeting")
public class BudgetForecastController {

    private final BudgetForecastService service;

    public BudgetForecastController(BudgetForecastService service) {
        this.service = service;
    }

    @GetMapping
    public String list(@ModelAttribute BudgetForecastFilter filter, Model model) {
        Page<BudgetForecast> page = service.search(filter);

        model.addAttribute("filter", filter);
        model.addAttribute("page", page);
        model.addAttribute("items", page.getContent());
        model.addAttribute("clientNames", service.allClientNames());
        model.addAttribute("financialYears", service.allFinancialYears());
        model.addAttribute("types", BudgetType.values());
        model.addAttribute("periods", BudgetPeriod.values());
        model.addAttribute("statuses", BudgetStatus.values());
        return "budgeting/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        if (!model.containsAttribute("itemForm")) {
            model.addAttribute("itemForm", new BudgetForecastForm());
        }
        model.addAttribute("types", BudgetType.values());
        model.addAttribute("periods", BudgetPeriod.values());
        model.addAttribute("statuses", BudgetStatus.values());
        model.addAttribute("formAction", "/budgeting");
        model.addAttribute("formTitle", "New Budget / Forecast");
        return "budgeting/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("itemForm") BudgetForecastForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("types", BudgetType.values());
            model.addAttribute("periods", BudgetPeriod.values());
            model.addAttribute("statuses", BudgetStatus.values());
            model.addAttribute("formAction", "/budgeting");
            model.addAttribute("formTitle", "New Budget / Forecast");
            return "budgeting/form";
        }
        BudgetForecast item = service.create(form);
        redirectAttributes.addFlashAttribute("successMessage", item.getType().getLabel() + " created for " + item.getClientName() + ".");
        return "redirect:/budgeting";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("item", service.get(id));
        return "budgeting/view";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("itemForm")) {
            BudgetForecast item = service.get(id);
            BudgetForecastForm form = new BudgetForecastForm();
            form.setClientName(item.getClientName());
            form.setType(item.getType());
            form.setFinancialYearStart(item.getFinancialYearStart());
            form.setPeriod(item.getPeriod());
            form.setBudgetedAmount(item.getBudgetedAmount());
            form.setStatus(item.getStatus());
            model.addAttribute("itemForm", form);
        }
        model.addAttribute("types", BudgetType.values());
        model.addAttribute("periods", BudgetPeriod.values());
        model.addAttribute("statuses", BudgetStatus.values());
        model.addAttribute("formAction", "/budgeting/" + id + "/edit");
        model.addAttribute("formTitle", "Edit Budget / Forecast");
        model.addAttribute("itemId", id);
        return "budgeting/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                          @Valid @ModelAttribute("itemForm") BudgetForecastForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("types", BudgetType.values());
            model.addAttribute("periods", BudgetPeriod.values());
            model.addAttribute("statuses", BudgetStatus.values());
            model.addAttribute("formAction", "/budgeting/" + id + "/edit");
            model.addAttribute("formTitle", "Edit Budget / Forecast");
            model.addAttribute("itemId", id);
            return "budgeting/form";
        }
        BudgetForecast item = service.update(id, form);
        redirectAttributes.addFlashAttribute("successMessage", item.getType().getLabel() + " updated.");
        return "redirect:/budgeting/" + id;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String notFound() {
        return "redirect:/budgeting";
    }
}
