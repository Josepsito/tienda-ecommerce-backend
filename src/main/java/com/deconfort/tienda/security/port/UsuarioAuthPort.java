package com.deconfort.tienda.security.port;

import com.deconfort.tienda.security.model.UsuarioAuth;

public interface UsuarioAuthPort {

    UsuarioAuth getUsuarioByEmail(String email);

}
