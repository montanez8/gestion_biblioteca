package com.security.services.impl;

import com.security.repositories.LibroRepository;
import com.security.repositories.PrestamoRepository;
import com.security.repositories.entities.Libro;
import com.security.services.LibroService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LibroServiceImpl implements LibroService {

    private LibroRepository libroRepository;
    private PrestamoRepository prestamoRepository;


    @Override
    public Libro save(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public List<Libro> findAll() {
        return (List<Libro>) libroRepository.findAll();
    }


    @Override
    public boolean edit(Long id, Libro libro) {
        Optional<Libro> editLibro = libroRepository.findById(id);
        if (editLibro.isPresent() && libro.getInventario() > 0) {
            editLibro.get().setTitulo(libro.getTitulo());
            editLibro.get().setAutor(libro.getAutor());
            editLibro.get().setGenero(libro.getGenero());
            editLibro.get().setAgno(libro.getAgno());
            editLibro.get().setInventario(libro.getInventario());
            libroRepository.save(editLibro.get());
            return true;
        }
        return false;
    }


    @Override
    public boolean deleteById(Long id) {
        Optional<Libro> deleteLibro = libroRepository.findById(id);
        if (deleteLibro.isPresent()) {
            if (!prestamoRepository.existsByLibro(deleteLibro.get())) {
                libroRepository.delete(deleteLibro.get());
                return true;
            }
        }
        return false;
    }

}
