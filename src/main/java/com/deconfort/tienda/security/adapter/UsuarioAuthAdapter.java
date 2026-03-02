package com.deconfort.tienda.security.adapter;

import com.deconfort.tienda.security.model.UsuarioAuth;
import com.deconfort.tienda.security.port.UsuarioAuthPort;
import com.deconfort.tienda.usuarios.model.entity.Usuario;
import com.deconfort.tienda.usuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

@Component
public class UsuarioAuthAdapter implements UsuarioAuthPort {

    private final UsuarioRepository usuarioRepository;

    public UsuarioAuthAdapter(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioAuth getUsuarioByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        UsuarioAuth usuarioAuth = new UsuarioAuth();
        usuarioAuth.setEmail(usuario.getEmail());
        usuarioAuth.setPassword(usuario.getPassword());
        usuarioAuth.setEnabled(true);
        usuarioAuth.setRoles(usuario.getRoles()
                .stream()
                .map(Enum::name)
                .toList());

        return usuarioAuth;
    }
}
