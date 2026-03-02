package com.deconfort.tienda.usuarios.repository;

import com.deconfort.tienda.usuarios.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    @Query("SELECT u.id FROM Usuario u WHERE u.email = :email")
    Optional<Long> findIdByEmail(@Param("email") String email);
}
