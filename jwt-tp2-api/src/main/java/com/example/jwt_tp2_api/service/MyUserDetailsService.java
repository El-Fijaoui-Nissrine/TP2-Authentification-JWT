package com.example.jwt_tp2_api.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService  implements UserDetailsService {

    private  final InMemoryUserDetailsManager delegate;

    MyUserDetailsService(){
        UserDetails user= User.withUsername("user")
                .password("{noop}12345")
                .roles("USER")

                .build();
        UserDetails admin= User.withUsername("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build();

        this.delegate=new InMemoryUserDetailsManager(user,admin);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return delegate.loadUserByUsername(username);
    }
}
