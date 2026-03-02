package com.deconfort.tienda.token.service;

import com.deconfort.tienda.token.model.dto.TokenPayload;
import com.deconfort.tienda.token.model.dto.TokenRequest;


public interface ITokenService {
    String generate(TokenRequest request);

    TokenPayload validate(String token);
}