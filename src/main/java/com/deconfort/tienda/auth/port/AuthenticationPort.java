package com.deconfort.tienda.auth.port;

public interface AuthenticationPort {

    String authenticate(String email, String password);

}
