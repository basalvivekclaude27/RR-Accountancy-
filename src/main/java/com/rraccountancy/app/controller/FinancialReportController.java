package com.rraccountancy.app.controller;

import com.rraccountancy.app.domain.AccountStatus;
import com.rraccountancy.app.domain.FinancialReport;
import com.rraccountancy.app.domain.ReportType;
import com.rraccountancy.app.dto.FinancialReportFilter;
import com.rraccountancy.app.dto.FinancialReportForm;
import com.rraccountancy.app.service.FinancialReportService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/financial-reports")
public class FinancialReportController {

    private final FinancialReportService service;

    public FinancialReportController(FinancialReportService service) {
        this.service = service;
    }

    @GetMapping
    public String list(@ModelAttribute FinancialReportFilter filter, Model model) {
        Page<FinancialReport> page = service.search(filter);

        model.addAttribute("filter", filter);
        model.addAttribute("page", page);
        model.addAttribute("reports", page.getContent());
        model.addAttribute("clientNames", service.allClientNames());
        model.addAttribute("financialYears", service.allFinancialYears());
        model.addAttribute("reportTypes", ReportType.values());
        model.addAttribute("statuses", AccountStatus.values());
        return "financial-reports/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        if (!model.containsAttribute("reportForm")) {
            model.addAttribute("reportForm", new FinancialReportForm());
        }
        model.addAttribute("reportTypes", ReportType.values());
        model.addAttribute("statuses", AccountStatus.values());
        model.addAttribute("formAction", "/financial-reports");
        model.addAttribute("formTitle", "New Report / Account");
        return "financial-reports/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("reportForm") FinancialReportForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("reportTypes", ReportType.values());
            model.addAttribute("statuses", AccountStatus.values());
            model.addAttribute("formAction", "/financial-reports");
            model.addAttribute("formTitle", "New Report / Account");
            return "financial-reports/form";
        }
        FinancialReport report = service.create(form);
        redirectAttributes.addFlashAttribute("successMessage", report.getReportType().getLabel() + " created for " + report.getClientName() + ".");
        return "redirect:/financial-reports";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("report", service.get(id));
        return "financial-reports/view";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("reportForm")) {
            FinancialReport report = service.get(id);
            FinancialReportForm form = new FinancialReportForm();
            form.setClientName(report.getClientName());
            form.setReportType(report.getReportType());
            form.setPeriod(java.time.YearMonth.from(report.getPeriodDate()));
            form.setFinancialYearStart(report.getFinancialYearStart());
            form.setStatus(report.getStatus());
            model.addAttribute("reportForm", form);
        }
        model.addAttribute("reportTypes", ReportType.values());
        model.addAttribute("statuses", AccountStatus.values());
        model.addAttribute("formAction", "/financial-reports/" + id + "/edit");
        model.addAttribute("formTitle", "Edit Report / Account");
        model.addAttribute("reportId", id);
        return "financial-reports/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                          @Valid @ModelAttribute("reportForm") FinancialReportForm form,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("reportTypes", ReportType.values());
            model.addAttribute("statuses", AccountStatus.values());
            model.addAttribute("formAction", "/financial-reports/" + id + "/edit");
            model.addAttribute("formTitle", "Edit Report / Account");
            model.addAttribute("reportId", id);
            return "financial-reports/form";
        }
        FinancialReport report = service.update(id, form);
        redirectAttributes.addFlashAttribute("successMessage", report.getReportType().getLabel() + " updated.");
        return "redirect:/financial-reports/" + id;
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> download(@PathVariable Long id) {
        FinancialReport report = service.get(id);

        String body = "R & R Accountancy Services\n"
                + "===========================\n\n"
                + "Report: " + report.getReportType().getLabel() + "\n"
                + "Client: " + report.getClientName() + "\n"
                + "Period: " + report.getPeriodLabel() + "\n"
                + "Financial Year: " + report.getFinancialYearLabel() + "\n"
                + "Status: " + report.getStatus().getLabel() + "\n"
                + "Generated On: " + report.getGeneratedOnLabel() + "\n\n"
                + "This is a placeholder export. Full document generation will be wired up\n"
                + "once the underlying accounting data feeds are connected.\n";

        String filename = report.getReportType().name().toLowerCase() + "-" + report.getId() + ".txt";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename(filename).build().toString())
                .contentType(MediaType.TEXT_PLAIN)
                .body(body.getBytes(StandardCharsets.UTF_8));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String notFound() {
        return "redirect:/financial-reports";
    }
}
