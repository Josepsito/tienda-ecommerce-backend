package com.deconfort.tienda.authSecurity.adapter;


import com.deconfort.tienda.authSecurity.model.dto.TokenData;
import com.deconfort.tienda.authSecurity.model.dto.TokenRequestData;
import com.deconfort.tienda.authSecurity.port.TokenPort;
import com.deconfort.tienda.token.model.dto.TokenPayload;
import com.deconfort.tienda.token.model.dto.TokenRequest;
import com.deconfort.tienda.token.service.ITokenService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class JwtTokenAdapter implements TokenPort {

    private final ITokenService tokenService;
    private final ApplicationContext applicationContext;

    public JwtTokenAdapter(ITokenService tokenService, ApplicationContext applicationContext) {
        this.tokenService = tokenService;
        this.applicationContext = applicationContext;
    }

    @Override
    public TokenData verify(String token) {
        TokenPayload tokenPayload = tokenService.validate(token);

        if (tokenPayload == null) return null;

        return new TokenData(
                 tokenPayload.subject()
                ,tokenPayload.email()
                ,tokenPayload.roles()
                ,tokenPayload.issuedAt()
                ,tokenPayload.expiration()
        );
    }

    @Override
    public String generate(TokenRequestData request) {

        TokenRequest tokenRequest = new TokenRequest(
                request.subject(),
                request.email(),
                request.roles());

        return tokenService.generate(tokenRequest);
    }


}
