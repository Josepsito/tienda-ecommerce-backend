package com.deconfort.tienda.security.model;

import java.util.List;

public class UsuarioAuth {
    private String email;
    private String password;
    private boolean enabled;
    private List<String> roles;

    public UsuarioAuth(String email, String password, boolean enabled, List<String> roles) {
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    public UsuarioAuth() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
