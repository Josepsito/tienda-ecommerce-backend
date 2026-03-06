package com.deconfort.tienda.authSecurity.model.dto;

public record LoginRequest(
        String email,
        String password
){

}
