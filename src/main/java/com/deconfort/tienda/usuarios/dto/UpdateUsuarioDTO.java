package com.deconfort.tienda.usuarios.dto;

import java.time.LocalDate;

public record UpdateUsuarioDTO(
        String nombres,
        String apellidos,
        String email,
        String telefono,
        LocalDate fechaNacimiento
){

}
