package com.deconfort.tienda.auth.adapter;

import com.deconfort.tienda.auth.model.dto.UsuarioDTO;
import com.deconfort.tienda.auth.port.UsuarioPort;
import com.deconfort.tienda.usuarios.model.entity.Usuario;
import com.deconfort.tienda.usuarios.port.UsuarioDataPort;
import com.deconfort.tienda.usuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioAdapter implements UsuarioPort {

    private final UsuarioDataPort usuarioDataPort;

    public UsuarioAdapter(UsuarioDataPort usuarioDataPort) {
        this.usuarioDataPort = usuarioDataPort;
    }

    @Override
    public UsuarioDTO getUserIdByEmail(String email) {

        Usuario usuario = usuarioDataPort.getUsuarioByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<String> roles = usuario.getRoles()
                .stream()
                .map(Enum::name)
                .toList();

        return new UsuarioDTO(
                usuario.getId(),
                usuario.getEmail(),
                roles
        );
    }
}