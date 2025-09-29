package com.cep.cep_api.auth.config;

import com.cep.cep_api.auth.domain.Passwords;
import com.cep.cep_api.auth.domain.Users;
import com.cep.cep_api.auth.repository.PasswordsRepository;
import com.cep.cep_api.auth.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class AppConfig {

    private final UsersRepository usersRepository;
    private final PasswordsRepository passwordsRepository;

    public AppConfig(UsersRepository usersRepository, PasswordsRepository passwordsRepository ) {
        this.usersRepository = usersRepository;
        this.passwordsRepository = passwordsRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            final Users user = usersRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            final Optional<Passwords> pass = passwordsRepository.findByUserId(user.getId());
            return org.springframework.security.core.userdetails.User
                    .builder()
                    .username(user.getEmail())
                    .password(pass.get().getPassword())
                    .build();
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

