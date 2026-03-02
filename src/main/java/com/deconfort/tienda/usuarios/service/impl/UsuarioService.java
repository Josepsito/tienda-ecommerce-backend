package com.deconfort.tienda.usuarios.service.impl;

import com.deconfort.tienda.usuarios.dto.CreateUsuarioDTO;
import com.deconfort.tienda.usuarios.dto.UpdateUsuarioDTO;
import com.deconfort.tienda.usuarios.dto.UsuarioDTO;
import com.deconfort.tienda.usuarios.model.Rol;
import com.deconfort.tienda.usuarios.model.entity.Usuario;
import com.deconfort.tienda.usuarios.repository.UsuarioRepository;
import com.deconfort.tienda.usuarios.service.IUsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorId(long id){

        UsuarioDTO usuario = usuarioRepository.findById(id)
                .map(u -> new UsuarioDTO(
                        u.getNombres(),
                        u.getApellidos(),
                        u.getEmail(),
                        u.getTelefono(),
                        u.getFechaNacimiento(),
                        u.getRoles().stream().map(Enum::name).toList()
                ))
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return usuario;
    }

    @Override
    public Page<UsuarioDTO> obtenerUsuariosPaginados(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("nombres").ascending());
        return usuarioRepository.findAll(pageable)
                .map(usuario -> new UsuarioDTO(
                        usuario.getNombres(),
                        usuario.getApellidos(),
                        usuario.getEmail(),
                        usuario.getTelefono(),
                        usuario.getFechaNacimiento(),
                        usuario.getRoles().stream().map(Enum::name).toList()
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

        usuarioRepository.save(usuarioNuevo);

    }

    @Override
    public UpdateUsuarioDTO actualizarUsuario(long idUsuario, UpdateUsuarioDTO nuevoUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (nuevoUsuario.nombres() != null) usuario.setNombres(nuevoUsuario.nombres());
        if (nuevoUsuario.apellidos() != null) usuario.setApellidos(nuevoUsuario.apellidos());
        if (nuevoUsuario.email() != null) usuario.setEmail(nuevoUsuario.email());
        if (nuevoUsuario.telefono() != null) usuario.setTelefono(nuevoUsuario.telefono());
        if (nuevoUsuario.fechaNacimiento() != null) usuario.setFechaNacimiento(nuevoUsuario.fechaNacimiento());

        usuarioRepository.save(usuario);

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
        usuarioRepository.deleteById(idUsuario);
    }

}
