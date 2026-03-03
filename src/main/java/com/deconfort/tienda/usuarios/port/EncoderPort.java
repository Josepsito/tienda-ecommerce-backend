package com.deconfort.tienda.usuarios.port;

import org.jspecify.annotations.Nullable;

public interface EncoderPort {

    String encode(@Nullable String password);

}
