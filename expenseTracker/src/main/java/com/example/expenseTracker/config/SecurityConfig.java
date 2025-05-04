package com.example.expenseTracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /** Delegating encoder -> BCrypt today, easily swappable later */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // disable CSRF
                .csrf(AbstractHttpConfigurer::disable)
                // allow every request (no authentication)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );

        // **nothing else**: by not calling formLogin() or httpBasic() at all,
        // you won’t get a default login page or HTTP‐Basic challenge.
        return http.build();
    }
}
