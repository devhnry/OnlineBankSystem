package org.henry.onlinebankingsystemp.config;

import lombok.RequiredArgsConstructor;
import org.henry.onlinebankingsystemp.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurityAuthProvider {

    private final UserDetailService userDetailService;
    private final SecurityPasswordEncoder passwordEncoder;


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder.passwordEncoder());
        return daoAuthenticationProvider;
    }
}
