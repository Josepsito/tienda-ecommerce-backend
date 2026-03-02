package com.deconfort.tienda.auth.port;

import com.deconfort.tienda.auth.model.dto.TokenData;
import com.deconfort.tienda.auth.model.dto.TokenRequestData;

public interface TokenPort {
    TokenData verify(String token);
    String generate(TokenRequestData request);
}
