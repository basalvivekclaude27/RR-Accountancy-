package com.rraccountancy.app.controller;

import com.rraccountancy.app.domain.BookkeepingJob;
import com.rraccountancy.app.domain.JobCategory;
import com.rraccountancy.app.domain.JobStatus;
import com.rraccountancy.app.dto.BookkeepingJobFilter;
import com.rraccountancy.app.dto.BookkeepingJobForm;
import com.rraccountancy.app.service.BookkeepingJobService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/bookkeeping")
public class BookkeepingController {

    private final BookkeepingJobService service;

    public BookkeepingController(BookkeepingJobService service) {
        this.service = service;
    }

    @GetMapping
    public String list(@ModelAttribute BookkeepingJobFilter filter, Model model) {
        Page<BookkeepingJob> page = service.search(filter);

        model.addAttribute("filter", filter);
        model.addAttribute("page", page);
        model.addAttribute("jobs", page.getContent());
        model.addAttribute("clientNames", service.allClientNames());
        model.addAttribute("assignees", service.allAssignees());
        model.addAttribute("categories", JobCategory.values());
        model.addAttribute("statuses", JobStatus.values());
        return "bookkeeping/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        if (!model.containsAttribute("jobForm")) {
            model.addAttribute("jobForm", new BookkeepingJobForm());
        }
        model.addAttribute("categories", JobCategory.values());
        model.addAttribute("statuses", JobStatus.values());
        model.addAttribute("formAction", "/bookkeeping");
        model.addAttribute("formTitle", "New Bookkeeping Job");
        return "bookkeeping/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("jobForm") BookkeepingJobForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", JobCategory.values());
            model.addAttribute("statuses", JobStatus.values());
            model.addAttribute("formAction", "/bookkeeping");
            model.addAttribute("formTitle", "New Bookkeeping Job");
            return "bookkeeping/form";
        }
        BookkeepingJob job = service.create(form);
        redirectAttributes.addFlashAttribute("successMessage", "Job " + job.getInvoiceRef() + " created for " + job.getClientName() + ".");
        return "redirect:/bookkeeping";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("job", service.get(id));
        return "bookkeeping/view";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("jobForm")) {
            BookkeepingJob job = service.get(id);
            BookkeepingJobForm form = new BookkeepingJobForm();
            form.setClientName(job.getClientName());
            form.setInvoiceRef(job.getInvoiceRef());
            form.setPeriod(java.time.YearMonth.from(job.getPeriodDate()));
            form.setCategory(job.getCategory());
            form.setStatus(job.getStatus());
            form.setAssignedTo(job.getAssignedTo());
            model.addAttribute("jobForm", form);
        }
        model.addAttribute("categories", JobCategory.values());
        model.addAttribute("statuses", JobStatus.values());
        model.addAttribute("formAction", "/bookkeeping/" + id + "/edit");
        model.addAttribute("formTitle", "Edit Bookkeeping Job");
        model.addAttribute("jobId", id);
        return "bookkeeping/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                          @Valid @ModelAttribute("jobForm") BookkeepingJobForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", JobCategory.values());
            model.addAttribute("statuses", JobStatus.values());
            model.addAttribute("formAction", "/bookkeeping/" + id + "/edit");
            model.addAttribute("formTitle", "Edit Bookkeeping Job");
            model.addAttribute("jobId", id);
            return "bookkeeping/form";
        }
        BookkeepingJob job = service.update(id, form);
        redirectAttributes.addFlashAttribute("successMessage", "Job " + job.getInvoiceRef() + " updated.");
        return "redirect:/bookkeeping/" + id;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String notFound() {
        return "redirect:/bookkeeping";
    }
}
