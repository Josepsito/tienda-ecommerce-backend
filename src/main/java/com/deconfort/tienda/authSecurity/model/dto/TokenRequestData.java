package com.deconfort.tienda.authSecurity.model.dto;

import java.util.List;

public record TokenRequestData(
        String subject,
        String email,
        List<String> roles
) {}