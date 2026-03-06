package com.deconfort.tienda.authSecurity.service;


import com.deconfort.tienda.authSecurity.model.dto.LoginRequest;
import com.deconfort.tienda.authSecurity.model.dto.TokenRequestData;
import com.deconfort.tienda.authSecurity.model.dto.UsuarioResponseLogin;
import com.deconfort.tienda.authSecurity.port.TokenPort;
import com.deconfort.tienda.authSecurity.port.UsuarioLoginPort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
public class AuthService implements IAuthService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioLoginPort usuarioLoginPort;
    private final TokenPort tokenPort;

    public AuthService(AuthenticationManager authenticationManager, UsuarioLoginPort usuarioLoginPort, TokenPort tokenPort) {
        this.authenticationManager = authenticationManager;
        this.usuarioLoginPort = usuarioLoginPort;
        this.tokenPort = tokenPort;
    }


    @Override
    public String login(LoginRequest loginRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                loginRequest.email(), loginRequest.password()
        ));

        UsuarioResponseLogin usuarioResponseLogin = usuarioLoginPort.getUserByEmail(loginRequest.email());

        String id = String.valueOf(usuarioResponseLogin.id());

        TokenRequestData tokenRequestData = new TokenRequestData(id, usuarioResponseLogin.email(), usuarioResponseLogin.roles());

        String token = tokenPort.generate(tokenRequestData);

        return token;
    }

}
