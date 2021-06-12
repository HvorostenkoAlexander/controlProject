package com.gmail.hvorostenko.web.configuration;

import com.gmail.hvorostenko.service.model.RoleUserEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.Collections;

@Configuration
public class UserDetailsConfig {

    @Bean
    public UserDetailsService userDetails(){
        return username -> new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
               String role = RoleUserEnum.SECURE_API_USER.name();
                GrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
                return Collections.singletonList(simpleGrantedAuthority);
            }

            @Override
            public String getPassword() {
                return new BCryptPasswordEncoder().encode("1234");
            }

            @Override
            public String getUsername() {
                return null;
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }
}
