package com.deconfort.tienda.auth.adapter;

import com.deconfort.tienda.auth.port.AuthenticationPort;
import com.deconfort.tienda.auth.service.dto.LoginRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class SecurityAuthenticationAdapter implements AuthenticationPort {

    private final AuthenticationManager authenticationManager;

    public SecurityAuthenticationAdapter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String authenticate(String email, String password){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email,password)
        );
        return authentication.getName();
    }

}
