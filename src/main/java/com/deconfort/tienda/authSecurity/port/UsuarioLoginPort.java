package com.deconfort.tienda.authSecurity.port;

import com.deconfort.tienda.authSecurity.model.dto.UsuarioResponseLogin;

public interface UsuarioLoginPort {
    UsuarioResponseLogin getUserByEmail(String email);
}
