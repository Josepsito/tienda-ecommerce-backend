package com.deconfort.tienda.usuarios.adapter;

import com.deconfort.tienda.usuarios.model.entity.Usuario;
import com.deconfort.tienda.usuarios.port.UsuarioDataPort;
import com.deconfort.tienda.usuarios.repository.UsuarioRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
@Profile("mysql")
public class UsuariosMysqlAdapter implements UsuarioDataPort {

    private final UsuarioRepository usuarioRepository;

    public UsuariosMysqlAdapter(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;

    }


    @Override
    public Optional<Usuario> getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Optional<Usuario> getUsuarioById(long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Page<Usuario> getAllPage(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    @Override
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public void eliminarUsuario(long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        System.out.println(usuario.toString());
        return usuarioRepository.save(usuario);
    }


}
