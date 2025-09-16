package com.sisko.exam.service;

import com.sisko.exam.master.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepo;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var u = userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new User(u.getEmail(), u.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_" + u.getRole().name())));
    }
}
