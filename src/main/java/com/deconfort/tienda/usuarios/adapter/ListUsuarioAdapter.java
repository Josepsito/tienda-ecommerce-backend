package com.deconfort.tienda.usuarios.adapter;

import com.deconfort.tienda.usuarios.model.Rol;
import com.deconfort.tienda.usuarios.model.entity.Usuario;
import com.deconfort.tienda.usuarios.port.UsuarioDataPort;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Profile("list")
public class ListUsuarioAdapter implements UsuarioDataPort {

    List<Usuario> usuarios = new ArrayList<>(
            List.of(
                    new Usuario(4L,"Pepe","Condori Chauca",
                            "joseito97.15.jc@gmail.com","123456","922018160",
                            LocalDate.now(),LocalDate.now(),List.of(Rol.ADMIN)),

                    new Usuario(2L,"Condor","Condori Chauca",
                            "joseito97.15.jcc@gmail.com","123456","922018160",
                            LocalDate.now(),LocalDate.now(),List.of(Rol.USER))
            )
    );

    @Override
    public Optional<Usuario> getUsuarioByEmail(String email) {
        return usuarios.stream()
                .filter(usuario -> usuario.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public Optional<Usuario> getUsuarioById(long id) {
        return usuarios.stream()
                .filter(usuario -> usuario.getId().equals(id))
                .findFirst();
    }

    @Override
    public Page<Usuario> getAllPage(Pageable pageable) {

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), usuarios.size());

        List<Usuario> pageContent = usuarios.subList(start, end);

        return new PageImpl<>(pageContent, pageable, usuarios.size());
    }

    @Override
    public void eliminarUsuario(long id) {
        usuarios.removeIf(usuario -> usuario.getId().equals(id));
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {

        if (usuario==null) {
            return null;
        }

        if (usuario.getId() == null) {
            Long id = Math.abs(UUID.randomUUID().getMostSignificantBits());
            usuario.setId(id);
            usuarios.add(usuario);
        } else {
            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i).getId().equals(usuario.getId())) {
                    usuarios.set(i, usuario);
                    return usuario;
                }
            }
            usuarios.add(usuario);
        }

        System.out.println(usuario.toString());
        System.out.println(usuarios.size());

        return usuario;
    }

    @Override
    public List<Usuario> getAll() {
        return usuarios;
    }

}
