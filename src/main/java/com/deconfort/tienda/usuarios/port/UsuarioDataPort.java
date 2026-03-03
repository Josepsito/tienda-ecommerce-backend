package com.deconfort.tienda.usuarios.port;

import com.deconfort.tienda.usuarios.model.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UsuarioDataPort {

    Optional<Usuario> getUsuarioByEmail(String email);
    Optional<Usuario> getUsuarioById(long id);
    Page<Usuario> getAllPage(Pageable pageable);
    void eliminarUsuario(long id);
    Usuario saveUsuario(Usuario usuario);
    List<Usuario> getAll();
}
