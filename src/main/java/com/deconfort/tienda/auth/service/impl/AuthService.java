package com.deconfort.tienda.auth.service.impl;

import com.deconfort.tienda.auth.model.dto.TokenRequestData;
import com.deconfort.tienda.auth.model.dto.UsuarioDTO;
import com.deconfort.tienda.auth.port.AuthenticationPort;
import com.deconfort.tienda.auth.port.TokenPort;
import com.deconfort.tienda.auth.port.UsuarioPort;
import com.deconfort.tienda.auth.service.IAuthService;
import com.deconfort.tienda.auth.service.dto.LoginRequest;
import org.springframework.stereotype.Service;


@Service
public class AuthService implements IAuthService {

    private final AuthenticationPort authenticationPort;
    private final TokenPort tokenPort;
    private final UsuarioPort usuarioPort;

    public AuthService(AuthenticationPort authenticationPort, TokenPort tokenPort, UsuarioPort usuarioPort) {
        this.authenticationPort = authenticationPort;
        this.tokenPort = tokenPort;
        this.usuarioPort = usuarioPort;
    }

    @Override
    public String login(LoginRequest loginRequest){
        authenticationPort.authenticate(loginRequest.email(), loginRequest.password());

        UsuarioDTO usuarioDTO = usuarioPort.getUserIdByEmail(loginRequest.email());

        String id = String.valueOf(usuarioDTO.id());

        TokenRequestData tokenRequestData = new TokenRequestData(id,usuarioDTO.email(),usuarioDTO.roles());

        String token = tokenPort.generate(tokenRequestData);

        return token;
    }

}
