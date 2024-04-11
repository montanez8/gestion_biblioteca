package com.security.dto.converters;

import java.util.Optional;

import com.security.dto.CrearPrestamoDTO;
import com.security.repositories.LibroRepository;
import com.security.repositories.RepositoryUser;
import com.security.repositories.entities.Libro;
import com.security.repositories.entities.Prestamo;
import org.springframework.stereotype.Component;

import com.security.repositories.entities.User;
import com.security.repositories.entities.enums.EstadoPrestamo;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CrearPrestamoDTOConvert {

    private RepositoryUser repositoryUser;
    private LibroRepository libroRepository;

    public CrearPrestamoDTO convertDTO(Prestamo prestamo) {
        CrearPrestamoDTO result = new CrearPrestamoDTO();
        result.setCodigo_libro(prestamo.getLibro().getId());
        result.setEmailUsuario(prestamo.getUsuario().getUsername());
        result.setDevolucion(prestamo.getDevolucion());
        result.setEstado(prestamo.getEstado());
        return result;
    }

    @SuppressWarnings("null")
    public Prestamo convertEntity(CrearPrestamoDTO dto) {
        Optional<Libro> libro = libroRepository.findById(dto.getCodigo_libro());
        Optional<User> usuario = repositoryUser.findByUsername(dto.getEmailUsuario());
        if (libro.isPresent() && usuario.isPresent()) {
            Prestamo prestamoEntity = new Prestamo();
            prestamoEntity.setLibro(libro.get());
            prestamoEntity.setUsuario(usuario.get());
            prestamoEntity.setPrestamo(dto.getPrestamo());
            prestamoEntity.setDevolucion(dto.getDevolucion());
            prestamoEntity.setEstado(EstadoPrestamo.SOLICITADO);
            return prestamoEntity;
        }else{
            return null;
        }
    }
}
