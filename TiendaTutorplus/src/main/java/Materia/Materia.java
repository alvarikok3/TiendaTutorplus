package com.tiendatutorplus.model;


public class Materia {
    private String nombre;
    private String imagen;

    public Materia(String nombre, String imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getImagen() {
        return imagen;
    }
}
