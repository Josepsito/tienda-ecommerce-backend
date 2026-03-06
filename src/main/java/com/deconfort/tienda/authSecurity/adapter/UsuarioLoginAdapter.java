package com.deconfort.tienda.authSecurity.adapter;


import com.deconfort.tienda.authSecurity.model.dto.UsuarioResponseLogin;
import com.deconfort.tienda.authSecurity.port.UsuarioLoginPort;
import com.deconfort.tienda.usuarios.model.entity.Usuario;
import com.deconfort.tienda.usuarios.port.UsuarioDataPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioLoginAdapter implements UsuarioLoginPort {

    private final UsuarioDataPort usuarioDataPort;

    public UsuarioLoginAdapter(UsuarioDataPort usuarioDataPort) {
        this.usuarioDataPort = usuarioDataPort;
    }

    @Override
    public UsuarioResponseLogin getUserByEmail(String email) {

        Usuario usuario = usuarioDataPort.getUsuarioByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<String> roles = usuario.getRoles()
                .stream()
                .map(Enum::name)
                .toList();

        return new UsuarioResponseLogin(
                usuario.getId(),
                usuario.getEmail(),
                roles
        );
    }
}