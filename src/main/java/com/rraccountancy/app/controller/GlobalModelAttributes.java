package com.rraccountancy.app.controller;

import com.rraccountancy.app.domain.Role;
import com.rraccountancy.app.security.AppUserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Makes the signed-in user's display name/role available to every view that
 * uses the shared app-topbar fragment, without every controller repeating
 * the same two lines.
 */
@ControllerAdvice
public class GlobalModelAttributes {

    @ModelAttribute("fullName")
    public String fullName(@AuthenticationPrincipal AppUserPrincipal principal) {
        return principal == null ? null : principal.getFullName();
    }

    @ModelAttribute("roleLabel")
    public String roleLabel(@AuthenticationPrincipal AppUserPrincipal principal) {
        if (principal == null) {
            return null;
        }
        return principal.getUser().getRole() == Role.ROLE_ADMIN ? "Administrator" : "Client";
    }
}
