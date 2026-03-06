package com.deconfort.tienda.authSecurity.port;

import com.deconfort.tienda.authSecurity.model.dto.UsuarioAuth;

public interface UsuarioAuthPort {
    UsuarioAuth getUsuarioByEmail(String email);
}
