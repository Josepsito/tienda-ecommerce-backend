package com.deconfort.tienda.security.filter;

import com.deconfort.tienda.security.port.JwtVerifierPort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtVerifierPort jwtVerifierPort;

    public JwtAuthenticationFilter(JwtVerifierPort jwtVerifierPort) {
        this.jwtVerifierPort = jwtVerifierPort;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String token = null;

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }

        if (token == null && request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("JWT".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token != null) {

            try {
                boolean tokenData = jwtVerifierPort.verify(token);

                if (!tokenData) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Token inválido o expirado");
                    return;
                }

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                jwtVerifierPort.getSubject(token),
                                null,
                                jwtVerifierPort.getRoles(token).stream()
                                        .map(SimpleGrantedAuthority::new)
                                        .toList()
                        );

                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Error interno del servidor");
                return;
            }

        }

        filterChain.doFilter(request, response);
    }
}