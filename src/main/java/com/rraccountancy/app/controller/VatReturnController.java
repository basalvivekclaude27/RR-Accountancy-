package com.rraccountancy.app.controller;

import com.rraccountancy.app.domain.TaxReturnStatus;
import com.rraccountancy.app.domain.VatReturn;
import com.rraccountancy.app.dto.VatReturnFilter;
import com.rraccountancy.app.dto.VatReturnForm;
import com.rraccountancy.app.service.VatReturnService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/value-added-tax")
public class VatReturnController {

    private final VatReturnService service;

    public VatReturnController(VatReturnService service) {
        this.service = service;
    }

    @GetMapping
    public String list(@ModelAttribute VatReturnFilter filter, Model model) {
        Page<VatReturn> page = service.search(filter);

        model.addAttribute("filter", filter);
        model.addAttribute("page", page);
        model.addAttribute("returns", page.getContent());
        model.addAttribute("clientTypes", service.allClientTypes());
        model.addAttribute("schemeTypes", service.allSchemeTypes());
        model.addAttribute("periods", service.allPeriods());
        model.addAttribute("statuses", TaxReturnStatus.values());
        model.addAttribute("pageNumbers", pageWindow(page.getNumber(), page.getTotalPages()));
        return "value-added-tax/list";
    }

    /** Windowed page-number list for the ellipsis pagination bar; -1 is the "…" sentinel. */
    private List<Integer> pageWindow(int currentZeroBased, int totalPages) {
        List<Integer> result = new ArrayList<>();
        if (totalPages <= 0) {
            result.add(1);
            return result;
        }
        int current = currentZeroBased + 1;
        if (totalPages <= 7) {
            for (int i = 1; i <= totalPages; i++) result.add(i);
            return result;
        }
        if (current <= 4) {
            for (int i = 1; i <= 5; i++) result.add(i);
            result.add(-1);
            result.add(totalPages);
        } else if (current >= totalPages - 3) {
            result.add(1);
            result.add(-1);
            for (int i = totalPages - 4; i <= totalPages; i++) result.add(i);
        } else {
            result.add(1);
            result.add(-1);
            for (int i = current - 1; i <= current + 1; i++) result.add(i);
            result.add(-1);
            result.add(totalPages);
        }
        return result;
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        if (!model.containsAttribute("returnForm")) {
            model.addAttribute("returnForm", new VatReturnForm());
        }
        model.addAttribute("statuses", TaxReturnStatus.values());
        model.addAttribute("formAction", "/value-added-tax");
        model.addAttribute("formTitle", "New VAT Enquiry");
        return "value-added-tax/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("returnForm") VatReturnForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", TaxReturnStatus.values());
            model.addAttribute("formAction", "/value-added-tax");
            model.addAttribute("formTitle", "New VAT Enquiry");
            return "value-added-tax/form";
        }
        VatReturn vatReturn = service.create(form);
        redirectAttributes.addFlashAttribute("successMessage", "VAT return created for " + vatReturn.getClientName() + ".");
        return "redirect:/value-added-tax";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("vatReturn", service.get(id));
        return "value-added-tax/view";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("returnForm")) {
            VatReturn vatReturn = service.get(id);
            VatReturnForm form = new VatReturnForm();
            form.setClientName(vatReturn.getClientName());
            form.setVatNumber(vatReturn.getVatNumber());
            form.setSchemeType(vatReturn.getSchemeType());
            form.setClientType(vatReturn.getClientType());
            form.setPeriodStart(vatReturn.getPeriodStart());
            form.setSalesExVat(vatReturn.getSalesExVat());
            form.setPurchasesExVat(vatReturn.getPurchasesExVat());
            form.setVatAmount(vatReturn.getVatAmount());
            form.setStatus(vatReturn.getStatus());
            form.setFiledOn(vatReturn.getFiledOn());
            form.setAssignedTo(vatReturn.getAssignedTo());
            model.addAttribute("returnForm", form);
        }
        model.addAttribute("statuses", TaxReturnStatus.values());
        model.addAttribute("formAction", "/value-added-tax/" + id + "/edit");
        model.addAttribute("formTitle", "Edit VAT Return");
        model.addAttribute("returnId", id);
        return "value-added-tax/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                          @Valid @ModelAttribute("returnForm") VatReturnForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", TaxReturnStatus.values());
            model.addAttribute("formAction", "/value-added-tax/" + id + "/edit");
            model.addAttribute("formTitle", "Edit VAT Return");
            model.addAttribute("returnId", id);
            return "value-added-tax/form";
        }
        VatReturn vatReturn = service.update(id, form);
        redirectAttributes.addFlashAttribute("successMessage", "VAT return for " + vatReturn.getClientName() + " updated.");
        return "redirect:/value-added-tax/" + id;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String notFound() {
        return "redirect:/value-added-tax";
    }
}
