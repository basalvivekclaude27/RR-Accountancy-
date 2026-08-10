package com.rraccountancy.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(AppUserDetailsService userDetailsService,
                                                              PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public org.springframework.security.authentication.AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/login", "/register", "/forgot-password",
                    "/css/**", "/js/**", "/images/**", "/webjars/**", "/favicon.ico"
                ).permitAll()
                .requestMatchers("/dashboard/admin/**").hasRole("ADMIN")
                .requestMatchers("/dashboard/client/**").hasRole("CLIENT")
                .requestMatchers("/bookkeeping/**").hasRole("ADMIN")
                .requestMatchers("/accounts-preparation/**").hasRole("ADMIN")
                .requestMatchers("/payroll/**").hasRole("ADMIN")
                .requestMatchers("/financial-reports/**").hasRole("ADMIN")
                .requestMatchers("/budgeting/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("identifier")
                .passwordParameter("password")
                .successHandler(new RoleBasedAuthSuccessHandler())
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .rememberMe(remember -> remember
                .key("rr-accountancy-remember-me-key")
                .rememberMeParameter("remember-me")
                .tokenValiditySeconds(1209600)
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionFixation(fixation -> fixation.migrateSession())
                .sessionConcurrency(concurrency -> concurrency
                    .maximumSessions(1)
                    .expiredUrl("/login?expired=true")
                )
            )
            .exceptionHandling(ex -> ex
                .accessDeniedPage("/403")
            )
            .csrf(csrf -> csrf.csrfTokenRepository(
                org.springframework.security.web.csrf.CookieCsrfTokenRepository.withHttpOnlyFalse()
            ));

        return http.build();
    }
}
