package com.deconfort.tienda.authSecurity.port;

public interface AuthenticationPort {

    String authenticate(String email, String password);

}
