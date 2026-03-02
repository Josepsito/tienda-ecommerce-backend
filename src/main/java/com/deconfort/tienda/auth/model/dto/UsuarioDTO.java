package com.deconfort.tienda.auth.model.dto;

import java.util.List;

public record UsuarioDTO (
        long id,
        String email,
        List<String> roles
){

}
