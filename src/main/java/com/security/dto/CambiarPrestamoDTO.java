package com.security.dto;

import com.security.repositories.entities.enums.EstadoPrestamo;

import lombok.Data;

@Data
public class CambiarPrestamoDTO {
    private Long codigo_prestamo;
    private EstadoPrestamo estadoPrestamo;
}
