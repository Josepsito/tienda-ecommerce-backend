package com.deconfort.tienda.security.port;

import java.util.List;

public interface JwtVerifierPort {
    boolean verify(String token);
    String getSubject(String token);
    List<String> getRoles(String token);
}