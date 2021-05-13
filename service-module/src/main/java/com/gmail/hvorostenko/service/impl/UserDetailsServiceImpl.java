package com.gmail.hvorostenko.service.impl;

import com.gmail.hvorostenko.repository.UserRepository;
import com.gmail.hvorostenko.repository.model.User;
import com.gmail.hvorostenko.service.model.UserLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with email: %s was not found", email));
        }
        return new UserLogin(user);
    }
}
