package com.connecttosubh.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    UserDetailsService userDetailSevice;

    @Autowired
    AuthenticationManager manager;
    @Override
    public boolean login(String username, String password) {

        UserDetails userDetails = userDetailSevice.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        manager.authenticate(token);
        if(token.isAuthenticated())
        {
            // For SuccessFul authentication returned the result
            SecurityContextHolder.getContext().setAuthentication(token);

        }
        return token.isAuthenticated();
    }
}
