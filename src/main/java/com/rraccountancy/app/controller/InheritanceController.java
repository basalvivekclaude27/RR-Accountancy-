package com.rraccountancy.app.controller;

import com.rraccountancy.app.domain.InheritanceCase;
import com.rraccountancy.app.domain.TaxReturnStatus;
import com.rraccountancy.app.dto.InheritanceFilter;
import com.rraccountancy.app.dto.InheritanceForm;
import com.rraccountancy.app.service.InheritanceService;
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
@RequestMapping("/inheritance-tax")
public class InheritanceController {

    /** IHT case tracking only ever uses these 4 statuses — REFUND_ISSUED doesn't apply here. */
    private static final TaxReturnStatus[] STATUSES = {
            TaxReturnStatus.PROCESSING, TaxReturnStatus.DOCUMENTS_PENDING,
            TaxReturnStatus.IN_REVIEW, TaxReturnStatus.FILED
    };

    private final InheritanceService service;

    public InheritanceController(InheritanceService service) {
        this.service = service;
    }

    @GetMapping
    public String list(@ModelAttribute InheritanceFilter filter, Model model) {
        Page<InheritanceCase> page = service.search(filter);

        model.addAttribute("filter", filter);
        model.addAttribute("page", page);
        model.addAttribute("cases", page.getContent());
        model.addAttribute("clientTypes", service.allClientTypes());
        model.addAttribute("planningTypes", service.allPlanningTypes());
        model.addAttribute("taxYears", service.allTaxYears());
        model.addAttribute("statuses", STATUSES);
        model.addAttribute("pageNumbers", pageWindow(page.getNumber(), page.getTotalPages()));
        return "inheritance-tax/list";
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
            model.addAttribute("caseForm", new InheritanceForm());
        }
        model.addAttribute("statuses", STATUSES);
        model.addAttribute("formAction", "/inheritance-tax");
        model.addAttribute("formTitle", "New IHT Enquiry");
        return "inheritance-tax/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("caseForm") InheritanceForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", STATUSES);
            model.addAttribute("formAction", "/inheritance-tax");
            model.addAttribute("formTitle", "New IHT Enquiry");
            return "inheritance-tax/form";
        }
        InheritanceCase iht = service.create(form);
        redirectAttributes.addFlashAttribute("successMessage", "IHT case created for " + iht.getClientName() + ".");
        return "redirect:/inheritance-tax";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("ihtCase", service.get(id));
        return "inheritance-tax/view";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("caseForm")) {
            InheritanceCase iht = service.get(id);
            InheritanceForm form = new InheritanceForm();
            form.setClientName(iht.getClientName());
            form.setUtr(iht.getUtr());
            form.setClientType(iht.getClientType());
            form.setPlanningType(iht.getPlanningType());
            form.setTaxYearStart(iht.getTaxYearStart());
            form.setEstateValue(iht.getEstateValue());
            form.setNilRateBandAvailable(iht.getNilRateBandAvailable());
            form.setTaxLiability(iht.getTaxLiability());
            form.setStatus(iht.getStatus());
            form.setAssignedTo(iht.getAssignedTo());
            model.addAttribute("caseForm", form);
        }
        model.addAttribute("statuses", STATUSES);
        model.addAttribute("formAction", "/inheritance-tax/" + id + "/edit");
        model.addAttribute("formTitle", "Edit IHT Case");
        model.addAttribute("caseId", id);
        return "inheritance-tax/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                          @Valid @ModelAttribute("caseForm") InheritanceForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", STATUSES);
            model.addAttribute("formAction", "/inheritance-tax/" + id + "/edit");
            model.addAttribute("formTitle", "Edit IHT Case");
            model.addAttribute("caseId", id);
            return "inheritance-tax/form";
        }
        InheritanceCase iht = service.update(id, form);
        redirectAttributes.addFlashAttribute("successMessage", "IHT case for " + iht.getClientName() + " updated.");
        return "redirect:/inheritance-tax/" + id;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String notFound() {
        return "redirect:/inheritance-tax";
    }
}
