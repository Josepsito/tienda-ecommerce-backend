package com.deconfort.tienda.auth.port;

import com.deconfort.tienda.auth.model.dto.UsuarioDTO;

public interface UsuarioPort {
    UsuarioDTO getUserByEmail(String email);
}