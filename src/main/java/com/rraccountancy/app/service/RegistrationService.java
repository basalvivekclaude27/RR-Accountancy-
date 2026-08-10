package com.rraccountancy.app.service;

import com.rraccountancy.app.domain.Role;
import com.rraccountancy.app.domain.User;
import com.rraccountancy.app.dto.RegistrationForm;
import com.rraccountancy.app.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean emailTaken(String email) {
        return userRepository.existsByEmail(email.trim().toLowerCase(Locale.ROOT));
    }

    @Transactional
    public User registerClient(RegistrationForm form) {
        String email = form.getEmail().trim().toLowerCase(Locale.ROOT);

        User user = new User();
        user.setUsername(email);
        user.setEmail(email);
        user.setFullName(form.getFullName().trim());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setRole(Role.ROLE_CLIENT);
        user.setEnabled(true);
        user.setAccountNonLocked(true);

        return userRepository.save(user);
    }
}
