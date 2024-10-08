package com.example.todolist.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Slf4j
public class SecurityConfiguration {
    private static final String ADMIN_ROLE = "ADMIN";
    private static final String USER_ROLE = "USER";

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        try {
            auth.inMemoryAuthentication()
                .withUser("user")
                .password("{noop}user")
                .roles(USER_ROLE);
            auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}admin")
                .roles(ADMIN_ROLE);
        } catch (Exception e) {
            log.error("Something is wrong!", e);
        }
    }

    /*
    channel: Require secure channel for all requests
    authorize:
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.requiresChannel(channel -> channel.anyRequest()
                                               .requiresSecure())
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/api/todolist/**")
                    .hasAnyRole(ADMIN_ROLE)
                    .requestMatchers("/todolist/users")
                    .hasAnyRole(USER_ROLE, ADMIN_ROLE)
                    .requestMatchers("/todolist/register", "/")
                    .permitAll())
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
