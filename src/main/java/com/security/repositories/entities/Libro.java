package com.security.repositories.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "libros")
@Data
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String genero;
    @Column(name = "anno")
    private int agno;
    private int inventario;

    public boolean existeInventario() {
        return --inventario >= 0;
    }

    public void libroPrestado() {
        if (existeInventario()) {
            inventario--;
        }
    }
}
