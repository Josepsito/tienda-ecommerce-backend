package com.deconfort.tienda.auth.adapter;

import com.deconfort.tienda.auth.model.dto.UsuarioDTO;
import com.deconfort.tienda.auth.port.UsuarioPort;
import com.deconfort.tienda.usuarios.model.entity.Usuario;
import com.deconfort.tienda.usuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioAdapter implements UsuarioPort {

    private final UsuarioRepository usuarioRepository;

    public UsuarioAdapter(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioDTO getUserIdByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("Usuario no encontrado"));

        List<String> roles = usuario.getRoles().stream().map(Enum::name).toList();

        return new UsuarioDTO(usuario.getId(), usuario.getEmail(), roles);
    }
}
