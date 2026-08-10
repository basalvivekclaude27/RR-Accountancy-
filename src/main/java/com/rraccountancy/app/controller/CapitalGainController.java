package com.rraccountancy.app.controller;

import com.rraccountancy.app.domain.CapitalGainReturn;
import com.rraccountancy.app.domain.TaxReturnStatus;
import com.rraccountancy.app.dto.CapitalGainFilter;
import com.rraccountancy.app.dto.CapitalGainForm;
import com.rraccountancy.app.service.CapitalGainService;
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
@RequestMapping("/capital-gain-tax")
public class CapitalGainController {

    /** CGT case tracking only ever uses these 4 statuses — REFUND_ISSUED doesn't apply here. */
    private static final TaxReturnStatus[] STATUSES = {
            TaxReturnStatus.PROCESSING, TaxReturnStatus.DOCUMENTS_PENDING,
            TaxReturnStatus.IN_REVIEW, TaxReturnStatus.FILED
    };

    private final CapitalGainService service;

    public CapitalGainController(CapitalGainService service) {
        this.service = service;
    }

    @GetMapping
    public String list(@ModelAttribute CapitalGainFilter filter, Model model) {
        Page<CapitalGainReturn> page = service.search(filter);

        model.addAttribute("filter", filter);
        model.addAttribute("page", page);
        model.addAttribute("cases", page.getContent());
        model.addAttribute("clientTypes", service.allClientTypes());
        model.addAttribute("assetTypes", service.allAssetTypes());
        model.addAttribute("taxYears", service.allTaxYears());
        model.addAttribute("statuses", STATUSES);
        model.addAttribute("pageNumbers", pageWindow(page.getNumber(), page.getTotalPages()));
        return "capital-gain-tax/list";
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
        if (!model.containsAttribute("caseForm")) {
            model.addAttribute("caseForm", new CapitalGainForm());
        }
        model.addAttribute("statuses", STATUSES);
        model.addAttribute("formAction", "/capital-gain-tax");
        model.addAttribute("formTitle", "New CGT Enquiry");
        return "capital-gain-tax/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("caseForm") CapitalGainForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", STATUSES);
            model.addAttribute("formAction", "/capital-gain-tax");
            model.addAttribute("formTitle", "New CGT Enquiry");
            return "capital-gain-tax/form";
        }
        CapitalGainReturn cgt = service.create(form);
        redirectAttributes.addFlashAttribute("successMessage", "CGT case created for " + cgt.getClientName() + ".");
        return "redirect:/capital-gain-tax";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("cgtCase", service.get(id));
        return "capital-gain-tax/view";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("caseForm")) {
            CapitalGainReturn cgt = service.get(id);
            CapitalGainForm form = new CapitalGainForm();
            form.setClientName(cgt.getClientName());
            form.setUtr(cgt.getUtr());
            form.setClientType(cgt.getClientType());
            form.setAssetType(cgt.getAssetType());
            form.setTaxYearStart(cgt.getTaxYearStart());
            form.setDateOfDisposal(cgt.getDateOfDisposal());
            form.setGainOrLoss(cgt.getGainOrLoss());
            form.setTaxPayable(cgt.getTaxPayable());
            form.setStatus(cgt.getStatus());
            form.setAssignedTo(cgt.getAssignedTo());
            model.addAttribute("caseForm", form);
        }
        model.addAttribute("statuses", STATUSES);
        model.addAttribute("formAction", "/capital-gain-tax/" + id + "/edit");
        model.addAttribute("formTitle", "Edit CGT Case");
        model.addAttribute("caseId", id);
        return "capital-gain-tax/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                          @Valid @ModelAttribute("caseForm") CapitalGainForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", STATUSES);
            model.addAttribute("formAction", "/capital-gain-tax/" + id + "/edit");
            model.addAttribute("formTitle", "Edit CGT Case");
            model.addAttribute("caseId", id);
            return "capital-gain-tax/form";
        }
        CapitalGainReturn cgt = service.update(id, form);
        redirectAttributes.addFlashAttribute("successMessage", "CGT case for " + cgt.getClientName() + " updated.");
        return "redirect:/capital-gain-tax/" + id;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String notFound() {
        return "redirect:/capital-gain-tax";
    }
}
