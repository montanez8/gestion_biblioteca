package com.security.repositories;

import com.security.repositories.entities.Libro;
import org.springframework.data.repository.CrudRepository;

public interface LibroRepository extends CrudRepository<Libro, Long>{
    
}
