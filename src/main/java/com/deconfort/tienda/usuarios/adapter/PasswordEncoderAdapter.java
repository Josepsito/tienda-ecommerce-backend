package com.deconfort.tienda.usuarios.adapter;

import com.deconfort.tienda.usuarios.port.EncoderPort;
import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;



@Component
public class PasswordEncoderAdapter implements EncoderPort {

    private final PasswordEncoder encoder;

    public PasswordEncoderAdapter(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public String encode(@Nullable String password) {
        return encoder.encode(password);
    }
}
