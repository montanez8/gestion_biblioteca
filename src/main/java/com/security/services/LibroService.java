package com.security.services;

import java.util.List;

import com.security.repositories.entities.Libro;

public interface LibroService {
    Libro save(Libro libro);
    List<Libro> findAll();
    boolean edit(Long id, Libro libro);
    boolean deleteById(Long id);
}
