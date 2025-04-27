package com.grepp.gridncircle.infra.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Value("${remember-me.key}")
    private String rememberMeKey;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .build();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler(){
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request,
                    HttpServletResponse response, Authentication authentication)
                    throws IOException, ServletException {

                boolean isAdmin = authentication.getAuthorities()
                        .stream()
                        .anyMatch(authority ->
                                authority.getAuthority().equals("ROLE_ADMIN"));

                if(isAdmin){
                    response.sendRedirect("/admin");
                    return;
                }

                response.sendRedirect("/");
            }
        };

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(
                        (requests) -> requests
                                .requestMatchers(GET, "/", "/assets/**", "/download/**").permitAll()
                                .requestMatchers(GET, "/api/member/exists/*").permitAll()
                                .requestMatchers(GET, "/500").permitAll()
                                .requestMatchers(GET, "/order").permitAll()
                                .requestMatchers(POST, "/order").permitAll()
                                .requestMatchers(GET, "/menu/**").permitAll()
                                .requestMatchers(GET, "/member/signup", "/member/login").permitAll()
                                .requestMatchers(POST, "/member/login", "/member/signup").permitAll()
                                .requestMatchers(GET, "/order-check/**").permitAll()
                                .requestMatchers(POST, "/order-check/**").permitAll()
                                .requestMatchers(GET, "/payment/**").permitAll()
                                .requestMatchers(POST, "/payment/**").permitAll()
//                              .requestMatchers(GET, "/upload/img/beans/**").permitAll() //  메뉴 이미지 공개 위해 해당 폴더만 접근 허용
                                .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/member/login")
                        .usernameParameter("id")
                        .loginProcessingUrl("/member/login")
                        .defaultSuccessUrl("/")
                        .successHandler(successHandler())
                        .permitAll()
                )
                .rememberMe(rememberMe -> rememberMe.key(rememberMeKey))
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}