package com.tiendatutorplus.model;

import javax.persistence.*;

@Entity
@Table(name = "tutor")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String apellido1;
    private String apellido2;
    private String correo;
    private String especialidad;
    private String contrasena;

    // Campos NO persistentes (solo para la vista de tutoresdestacados)
    @Transient
    private Double calificacion;

    @Transient
    private String imagen;

    @Transient
    private String descripcion;

    @Transient
    private String modalidad;

    @Transient
    private String nivel;

    public Tutor() {

    }

    // Constructor para usar en controller "mock" de destacados
    public Tutor(String nombre, String especialidad, double calificacion, String imagen, String descripcion) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.calificacion = calificacion;
        this.imagen = imagen;
        this.descripcion = descripcion;
    }

    // ====== Getters/Setters persistentes ======
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    // ====== Getters/Setters NO persistentes ======
    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
