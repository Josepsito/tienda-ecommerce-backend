package com.deconfort.tienda.auth.adapter;

import com.deconfort.tienda.auth.model.dto.TokenData;
import com.deconfort.tienda.auth.model.dto.TokenRequestData;
import com.deconfort.tienda.auth.port.TokenPort;
import com.deconfort.tienda.token.model.dto.TokenPayload;
import com.deconfort.tienda.token.model.dto.TokenRequest;
import com.deconfort.tienda.token.service.ITokenService;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenAdapter implements TokenPort {

    private final ITokenService tokenService;

    public JwtTokenAdapter(ITokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public TokenData verify(String token) {
        TokenPayload tokenPayload = tokenService.validate(token);

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
