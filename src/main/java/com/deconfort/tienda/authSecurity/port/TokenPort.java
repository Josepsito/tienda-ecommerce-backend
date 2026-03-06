package com.deconfort.tienda.authSecurity.port;


import com.deconfort.tienda.authSecurity.model.dto.TokenData;
import com.deconfort.tienda.authSecurity.model.dto.TokenRequestData;

public interface TokenPort {

    TokenData verify(String token);

    String generate(TokenRequestData request);
}
