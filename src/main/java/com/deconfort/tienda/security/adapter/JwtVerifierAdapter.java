package com.deconfort.tienda.security.adapter;

import com.deconfort.tienda.auth.port.TokenPort;
import com.deconfort.tienda.security.port.JwtVerifierPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtVerifierAdapter implements JwtVerifierPort {

    private final TokenPort tokenPort;

    public JwtVerifierAdapter(TokenPort tokenPort) {
        this.tokenPort = tokenPort;
    }

    @Override
    public boolean verify(String token) {
        try {
            tokenPort.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getSubject(String token) {
        return tokenPort.verify(token).subject();
    }

    @Override
    public List<String> getRoles(String token) {
        return tokenPort.verify(token).roles();
    }
}