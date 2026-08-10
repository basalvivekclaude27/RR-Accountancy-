package com.rraccountancy.app.controller;

import com.rraccountancy.app.dto.RegistrationForm;
import com.rraccountancy.app.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    private final RegistrationService registrationService;

    public RegisterController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        if (!model.containsAttribute("registrationForm")) {
            model.addAttribute("registrationForm", new RegistrationForm());
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registrationForm") RegistrationForm form,
                            BindingResult bindingResult,
                            Model model,
                            RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors() && !form.isPasswordConfirmed()) {
            bindingResult.rejectValue("confirmPassword", "mismatch", "Passwords do not match");
        }
        if (!bindingResult.hasErrors() && registrationService.emailTaken(form.getEmail())) {
            bindingResult.rejectValue("email", "duplicate", "An account with this email already exists");
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        registrationService.registerClient(form);
        return "redirect:/login?registered=true&tab=client";
    }
}
