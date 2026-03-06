package com.deconfort.tienda.authSecurity.model.dto;

import java.util.List;

public record UsuarioResponseLogin(
        Long id,
        String email,
        List<String> roles
){

}
