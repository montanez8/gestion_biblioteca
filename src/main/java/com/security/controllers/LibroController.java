package com.security.controllers;

import com.security.repositories.entities.Libro;
import com.security.services.LibroService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@RestController
@RequestMapping("/libros")
public class LibroController {

    private LibroService libroService;

    @PostMapping("/")
    public ResponseEntity<Libro> save(@RequestBody Libro libro) {
        try {
            return new ResponseEntity<>(libroService.save(libro), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/")//localhost:8080/libros/
    public ResponseEntity<List<Libro>> findAll() {
        List<Libro> libros = libroService.findAll();
        if (!libros.isEmpty()) {
            return new ResponseEntity<>(libros, HttpStatus.OK);
        }
        return new ResponseEntity<>(libros, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/")
    public ResponseEntity<String> edit(@RequestParam Long id, @RequestBody Libro libro) {
        if (libroService.edit(id, libro)) {
            return new ResponseEntity<>("Libro editado correctamente", HttpStatus.OK);
        }
        return new ResponseEntity<>("Libro no encontrado", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> deleteById(@RequestParam Long id) {
        if (libroService.deleteById(id)) {
            return new ResponseEntity<>("Libro eliminado correctamente", HttpStatus.OK);
        }
        return new ResponseEntity<>("No se encontro el libro o ya tiene prestamos relacionados", HttpStatus.BAD_REQUEST);
    }
    
}
