package com.tiendatutorplus.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToMany(mappedBy = "roles")
    private Set<Usuario> usuarios;

    // Getters y Setters
}
