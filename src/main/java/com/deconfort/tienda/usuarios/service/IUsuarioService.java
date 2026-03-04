package com.deconfort.tienda.usuarios.service;

import com.deconfort.tienda.usuarios.dto.CreateUsuarioDTO;
import com.deconfort.tienda.usuarios.dto.UpdateUsuarioDTO;
import com.deconfort.tienda.usuarios.dto.UsuarioDTO;
import com.deconfort.tienda.usuarios.model.entity.Usuario;
import org.springframework.data.domain.Page;

import java.util.List;


public interface IUsuarioService {

    void nuevoUsuario(CreateUsuarioDTO usuario);

    UsuarioDTO obtenerUsuarioPorId(long id);

    Page<UsuarioDTO> obtenerUsuariosPaginados(int page, int size);

    UpdateUsuarioDTO actualizarUsuario(long idUsuario, UpdateUsuarioDTO nuevoUsuario);

    void eliminarUsuario(long idUsuario);

    List<UsuarioDTO> getAll();
}
