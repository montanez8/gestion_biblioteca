package com.security.dto.converters;

import com.security.dto.ListarPrestamoDTO;
import com.security.repositories.entities.Prestamo;
import org.springframework.stereotype.Component;

@Component
public class ListarPrestamoDTOConvert {

    public ListarPrestamoDTO convertDTO(Prestamo prestamo) {
        ListarPrestamoDTO result = new ListarPrestamoDTO();
        result.setCodigo_prestamo(prestamo.getId());
        result.setCodigo_Libro(prestamo.getLibro().getId());
        result.setTitulo(prestamo.getLibro().getTitulo());
        result.setEmail_usuario(prestamo.getUsuario().getUsername());
        result.setPrestamo(prestamo.getPrestamo());
        result.setDevolucion(prestamo.getDevolucion());
        result.setEstado(prestamo.getEstado());
        return result;
    }
}
