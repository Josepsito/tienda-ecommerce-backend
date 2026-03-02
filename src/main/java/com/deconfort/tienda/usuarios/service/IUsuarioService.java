package com.deconfort.tienda.usuarios.service;

import com.deconfort.tienda.usuarios.dto.CreateUsuarioDTO;
import com.deconfort.tienda.usuarios.dto.UpdateUsuarioDTO;
import com.deconfort.tienda.usuarios.dto.UsuarioDTO;
import org.springframework.data.domain.Page;


public interface IUsuarioService {

    void nuevoUsuario(CreateUsuarioDTO usuario);

    UsuarioDTO obtenerUsuarioPorId(long id);

    Page<UsuarioDTO> obtenerUsuariosPaginados(int page, int size);

    UpdateUsuarioDTO actualizarUsuario(long idUsuario, UpdateUsuarioDTO nuevoUsuario);

    void eliminarUsuario(long idUsuario);
}
