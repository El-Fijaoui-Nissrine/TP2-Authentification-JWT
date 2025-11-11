package com.example.jwt_tp2_api.controller;

import com.example.jwt_tp2_api.model.AuthRequest;
import com.example.jwt_tp2_api.model.AuthResponse;
import com.example.jwt_tp2_api.service.JwtService;
import com.example.jwt_tp2_api.service.MyUserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private MyUserDetailsService myUserDetailsService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, MyUserDetailsService myUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.myUserDetailsService = myUserDetailsService;
    }
    @PostMapping("/auth/login")
    public AuthResponse login(@RequestBody AuthRequest req) {
        var auth = new UsernamePasswordAuthenticationToken(req.username(), req.password());
        authenticationManager.authenticate(auth);
        UserDetails user = myUserDetailsService.loadUserByUsername(req.username());
        String token = jwtService.generateToken(user.getUsername(), Map.of("roles", user.getAuthorities()));
        return new AuthResponse(token);
    }

    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of("message", "Bonjour, endpoint protégé OK !!");
    }
}
