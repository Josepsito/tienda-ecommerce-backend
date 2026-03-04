package com.deconfort.tienda.usuarios.service.impl;

import com.deconfort.tienda.usuarios.dto.CreateUsuarioDTO;
import com.deconfort.tienda.usuarios.dto.UpdateUsuarioDTO;
import com.deconfort.tienda.usuarios.dto.UsuarioDTO;
import com.deconfort.tienda.usuarios.model.Rol;
import com.deconfort.tienda.usuarios.model.entity.Usuario;
import com.deconfort.tienda.usuarios.port.EncoderPort;
import com.deconfort.tienda.usuarios.port.UsuarioDataPort;
import com.deconfort.tienda.usuarios.service.IUsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    private final UsuarioDataPort usuarioDataPort;
    private final EncoderPort passwordEncoder;

    public UsuarioService(UsuarioDataPort usuarioDataPort, EncoderPort passwordEncoder) {
        this.usuarioDataPort = usuarioDataPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorId(long id){

        UsuarioDTO usuario = usuarioDataPort.getUsuarioById(id)
                .map(u -> new UsuarioDTO(
                        u.getNombres(),
                        u.getApellidos(),
                        u.getEmail(),
                        u.getTelefono(),
                        u.getFechaNacimiento(),
                        u.getRoles().stream().map(Enum::name).toList(),
                        u.isEstado()
                ))
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return usuario;
    }

    @Override
    public Page<UsuarioDTO> obtenerUsuariosPaginados(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("nombres").ascending());

        return usuarioDataPort.getAllPage(pageable)
                .map(usuario -> new UsuarioDTO(
                        usuario.getNombres(),
                        usuario.getApellidos(),
                        usuario.getEmail(),
                        usuario.getTelefono(),
                        usuario.getFechaNacimiento(),
                        usuario.getRoles().stream().map(Enum::name).toList(),
                        usuario.isEstado()
                ));
    }

    @Override
    public void nuevoUsuario(CreateUsuarioDTO usuario){

        Usuario usuarioNuevo = new Usuario();
        usuarioNuevo.setFechaNacimiento(usuario.fechaNacimiento());
        usuarioNuevo.setEmail(usuario.email());
        usuarioNuevo.setNombres(usuario.nombres());
        usuarioNuevo.setApellidos(usuario.apellidos());
        usuarioNuevo.setTelefono(usuario.telefono());
        usuarioNuevo.setPassword(passwordEncoder.encode(usuario.password()));
        usuarioNuevo.setRoles(List.of(Rol.USER));

        usuarioDataPort.saveUsuario(usuarioNuevo);

    }

    @Override
    public UpdateUsuarioDTO actualizarUsuario(long idUsuario, UpdateUsuarioDTO nuevoUsuario) {
        Usuario usuario = usuarioDataPort.getUsuarioById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (nuevoUsuario.nombres() != null) usuario.setNombres(nuevoUsuario.nombres());
        if (nuevoUsuario.apellidos() != null) usuario.setApellidos(nuevoUsuario.apellidos());
        if (nuevoUsuario.email() != null) usuario.setEmail(nuevoUsuario.email());
        if (nuevoUsuario.telefono() != null) usuario.setTelefono(nuevoUsuario.telefono());
        if (nuevoUsuario.fechaNacimiento() != null) usuario.setFechaNacimiento(nuevoUsuario.fechaNacimiento());

        usuarioDataPort.saveUsuario(usuario);

        return new UpdateUsuarioDTO(
                usuario.getNombres(),
                usuario.getApellidos(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getFechaNacimiento()
        );
    }

    @Override
    public void eliminarUsuario(long idUsuario){
        usuarioDataPort.eliminarUsuario(idUsuario);
    }

    @Override
    public List<UsuarioDTO> getAll() {

        List<Usuario> usuarios = usuarioDataPort.getAll();

        return usuarios.stream().map(usuario -> new UsuarioDTO(
                usuario.getNombres(),
                usuario.getApellidos(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getFechaNacimiento(),
                usuario.getRoles().stream().map(Enum::name).toList(),
                usuario.isEstado()
        )).toList();
    }


}
