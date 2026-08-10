package com.rraccountancy.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Controller
public class DashboardController {

    private static final DateTimeFormatter TOPBAR_DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);

    // fullName / roleLabel come from GlobalModelAttributes.

    @GetMapping("/dashboard/admin")
    public String adminDashboard(Model model) {
        model.addAttribute("todayLabel", LocalDate.now().format(TOPBAR_DATE_FORMAT));
        // KPI / chart / activity figures are static placeholders until Bookkeeping,
        // Accounts Preparation etc. modules exist to source real numbers from.
        return "dashboard/admin";
    }

    @GetMapping("/dashboard/client")
    public String clientDashboard() {
        return "dashboard/client";
    }
}
