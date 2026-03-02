package com.deconfort.tienda.usuarios.dto;

import java.time.LocalDate;

public record CreateUsuarioDTO (
        String nombres,
        String apellidos,
        String email,
        String telefono,
        String password,
        LocalDate fechaNacimiento
){
}
