package com.security.dto;

import java.util.Date;

import com.security.repositories.entities.enums.EstadoPrestamo;

import lombok.Data;

@Data
public class ListarPrestamoDTO {
    private Long codigo_prestamo;
    private Long codigo_Libro;
    private String titulo;
    private String email_usuario;
    private Date prestamo;
    private Date devolucion;
    private EstadoPrestamo estado;
}
