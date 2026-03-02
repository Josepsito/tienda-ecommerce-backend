package com.deconfort.tienda.auth.service;

import com.deconfort.tienda.auth.service.dto.LoginRequest;

public interface IAuthService {
    String login (LoginRequest loginRequest);
}
