package com.deconfort.tienda.token.model.dto;

import java.util.Date;
import java.util.List;

public record TokenPayload(
        String subject,
        String email,
        List<String> roles,
        Date issuedAt,
        Date expiration
) {}