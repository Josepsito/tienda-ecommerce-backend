package com.deconfort.tienda.usuarios.model.entity;

import com.deconfort.tienda.usuarios.model.Rol;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombres;
    private String apellidos;
    @Column(unique = true)
    private String email;
    private String password;
    private String telefono;
    private LocalDate fechaNacimiento;
    private LocalDate creadoEn;
    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private List<Rol> roles;

    public Usuario(Long id, String nombres, String apellidos, String email, String password, String telefono, LocalDate fechaNacimiento, LocalDate creadoEn, List<Rol> roles) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.creadoEn = creadoEn;
        this.roles = roles;
    }

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(LocalDate creadoEn) {
        this.creadoEn = creadoEn;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }
}
