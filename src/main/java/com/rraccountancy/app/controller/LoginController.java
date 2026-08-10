package com.rraccountancy.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            @RequestParam(value = "expired", required = false) String expired,
            @RequestParam(value = "registered", required = false) String registered,
            @RequestParam(value = "tab", required = false, defaultValue = "admin") String tab,
            Model model) {

        if (error != null) {
            model.addAttribute("errorMessage", "Invalid credentials. Please check and try again.");
        }
        if (logout != null) {
            model.addAttribute("infoMessage", "You have been signed out successfully.");
        }
        if (expired != null) {
            model.addAttribute("errorMessage", "Your session has expired. Please sign in again.");
        }
        if (registered != null) {
            model.addAttribute("infoMessage", "Account created successfully. Please sign in.");
        }
        model.addAttribute("activeTab", "client".equals(tab) ? "client" : "admin");
        return "login";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/login";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot-password";
    }

    // No method restriction: Spring Security's AccessDeniedHandler forwards here
    // preserving the original request method (GET, POST, ...), so this must
    // accept all of them rather than just GET.
    @RequestMapping("/403")
    public String accessDenied() {
        return "403";
    }
}
