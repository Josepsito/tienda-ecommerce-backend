package com.deconfort.tienda.auth.model.dto;

import java.util.Date;
import java.util.List;

public record TokenData(
        String subject,
        String email,
        List<String> roles,
        Date issuedAt,
        Date expiration
) {}