package com.deconfort.tienda.authSecurity.service;

import com.deconfort.tienda.authSecurity.model.dto.UsuarioAuth;

import com.deconfort.tienda.authSecurity.port.UsuarioAuthPort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioAuthPort usuarioAuthPort;

    public UserDetailsServiceImpl(UsuarioAuthPort usuarioAuthPort) {
        this.usuarioAuthPort = usuarioAuthPort;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsuarioAuth usuarioAuth = usuarioAuthPort.getUsuarioByEmail(username);

        if (usuarioAuth == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return usuarioAuth;
    }
}
