package com.deconfort.tienda.security.service;

import com.deconfort.tienda.security.model.UsuarioAuth;
import com.deconfort.tienda.security.port.UsuarioAuthPort;
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
