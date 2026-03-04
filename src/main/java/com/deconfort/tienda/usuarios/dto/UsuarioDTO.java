package com.deconfort.tienda.usuarios.dto;

import java.time.LocalDate;
import java.util.List;

public record UsuarioDTO (
        String nombres,
        String apellidos,
        String email,
        String telefono,
        LocalDate fechaNacimiento,
        List<String> rol,
        boolean activo
){
}
