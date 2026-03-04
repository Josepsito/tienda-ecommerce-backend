package com.deconfort.tienda.security.adapter;

import com.deconfort.tienda.security.model.UsuarioAuth;
import com.deconfort.tienda.security.port.UsuarioAuthPort;
import com.deconfort.tienda.usuarios.model.entity.Usuario;
import com.deconfort.tienda.usuarios.port.UsuarioDataPort;
import org.springframework.stereotype.Component;

@Component
public class UsuarioAuthAdapter implements UsuarioAuthPort {

    private final UsuarioDataPort usuarioDataPort;

    public UsuarioAuthAdapter(UsuarioDataPort usuarioDataPort) {
        this.usuarioDataPort = usuarioDataPort;
    }

    @Override
    public UsuarioAuth getUsuarioByEmail(String email) {

        Usuario usuario = usuarioDataPort
                .getUsuarioByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        UsuarioAuth usuarioAuth = new UsuarioAuth();
        usuarioAuth.setId(usuario.getId());
        usuarioAuth.setEmail(usuario.getEmail());
        usuarioAuth.setPassword(usuario.getPassword());
        usuarioAuth.setEnabled(true);
        usuarioAuth.setRoles(
                usuario.getRoles()
                        .stream()
                        .map(Enum::name)
                        .toList()
        );

        return usuarioAuth;
    }
}