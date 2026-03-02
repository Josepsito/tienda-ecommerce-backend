package com.deconfort.tienda.auth.controller;

import com.deconfort.tienda.auth.service.IAuthService;
import com.deconfort.tienda.auth.service.dto.LoginRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest,
                                        HttpServletResponse response) {

        String token = authService.login(loginRequest);

        ResponseCookie cookie = ResponseCookie.from("JWT", token)
                .httpOnly(true)
                .secure(false)  //Solo sera false para pruebas dev
                .path("/")
                .sameSite("Lax")
                .build();

        response.addHeader("Set-Cookie", cookie.toString());

        return ResponseEntity.ok("Login exitoso");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {

        ResponseCookie cookie = ResponseCookie.from("JWT", "")
                .httpOnly(true)
                .secure(false) //Solo sera false para pruebas dev
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();

        response.addHeader("Set-Cookie", cookie.toString());

        return ResponseEntity.ok("Logout exitoso");
    }

}
