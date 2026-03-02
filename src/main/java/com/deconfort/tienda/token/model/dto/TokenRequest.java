package com.deconfort.tienda.token.model.dto;

import java.util.List;

public record TokenRequest (
        String subject,
        String email,
        List<String> roles
){
}
