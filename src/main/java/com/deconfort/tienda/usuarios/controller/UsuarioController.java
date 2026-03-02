package com.deconfort.tienda.usuarios.controller;

import com.deconfort.tienda.usuarios.dto.CreateUsuarioDTO;
import com.deconfort.tienda.usuarios.dto.UpdateUsuarioDTO;
import com.deconfort.tienda.usuarios.dto.UsuarioDTO;
import com.deconfort.tienda.usuarios.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    private final IUsuarioService usuarioService;

    @Autowired
    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public Page<UsuarioDTO> listarUsuarios(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return usuarioService.obtenerUsuariosPaginados(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable long id) {
        try {
            UsuarioDTO usuario = usuarioService.obtenerUsuarioPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> crearUsuario(@RequestBody CreateUsuarioDTO usuario) {
        usuarioService.nuevoUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateUsuarioDTO> actualizarUsuario(
            @PathVariable long id,
            @RequestBody UpdateUsuarioDTO usuarioActualizado
    ) {
        try {
            UpdateUsuarioDTO usuario = usuarioService.actualizarUsuario(id, usuarioActualizado);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }
}