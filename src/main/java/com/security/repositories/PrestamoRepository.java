package com.security.repositories;

import com.security.repositories.entities.Libro;
import com.security.repositories.entities.Prestamo;
import org.springframework.data.repository.CrudRepository;

import com.security.repositories.entities.User;

public interface PrestamoRepository extends CrudRepository<Prestamo, Long>{
    boolean existsByUsuario(User usuario);
    boolean existsByLibro(Libro libro);
}
