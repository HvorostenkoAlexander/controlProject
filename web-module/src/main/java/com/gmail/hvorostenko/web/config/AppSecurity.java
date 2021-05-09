package com.gmail.hvorostenko.web.config;

import com.gmail.hvorostenko.service.model.RoleUserEnum;
import com.gmail.hvorostenko.web.config.handler.AppSuccessHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
@Order
public class AppSecurity extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public AppSecurity(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/administrator/**")
                .hasAuthority(RoleUserEnum.ADMINISTRATOR.name())
                .antMatchers("/customer/**")
                .hasAuthority(RoleUserEnum.CUSTOMER_USER.name())
                .antMatchers("/", "/login", "/denied-page")
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(new AppSuccessHandler())
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/denied-page")
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf()
                .disable();
    }
}