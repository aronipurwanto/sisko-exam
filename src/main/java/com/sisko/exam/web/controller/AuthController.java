package com.sisko.exam.web.controller;

import com.sisko.exam.security.JwtService;
import com.sisko.exam.service.CustomUserDetailsService;
import com.sisko.exam.web.dto.AuthDTOs;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


record LoginRequest(String username, String password) {}
record LoginResponse(String token) {}


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService uds;


    @PostMapping("/login")
    public ResponseEntity<AuthDTOs.LoginResp> login(@RequestBody AuthDTOs.LoginReq req) {
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(req.email(), req.password()));
        UserDetails principal = uds.loadUserByUsername(req.email());
        String token = jwtService.generateToken(principal, Map.of("role", principal.getAuthorities().iterator().next().getAuthority()));
        return ResponseEntity.ok(new AuthDTOs.LoginResp(token));
    }
}
