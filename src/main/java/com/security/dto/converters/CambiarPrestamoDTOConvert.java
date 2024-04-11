package com.security.dto.converters;

import java.util.Optional;

import com.security.repositories.PrestamoRepository;
import com.security.repositories.entities.Prestamo;
import com.security.repositories.entities.enums.EstadoPrestamo;
import org.springframework.stereotype.Component;

import com.security.dto.CambiarPrestamoDTO;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CambiarPrestamoDTOConvert {
    
    private PrestamoRepository prestamoRepository;

    @SuppressWarnings("null")
    public Prestamo convertEntity(CambiarPrestamoDTO dto) {
        Prestamo prestamo = new Prestamo();
        Optional<Prestamo> existPrestamo = prestamoRepository.findById(dto.getCodigo_prestamo());
        if (existPrestamo.isPresent()) {
            if (existPrestamo.get().getEstado() != EstadoPrestamo.CANCELADO) {
                prestamo = existPrestamo.get();
                prestamo.setEstado(dto.getEstadoPrestamo());
                return prestamo;
            }
        }
        return prestamo;
    }
}
