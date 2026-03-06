package com.deconfort.tienda.authSecurity.service;


import com.deconfort.tienda.authSecurity.model.dto.LoginRequest;

public interface IAuthService {

    String login(LoginRequest loginRequest);
}
