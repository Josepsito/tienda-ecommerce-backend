package com.deconfort.tienda.auth.model.dto;

import java.util.List;

public record TokenRequestData(
        String subject,
        String email,
        List<String> roles
) {}