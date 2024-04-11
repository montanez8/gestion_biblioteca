package com.security.dto;

import java.util.Date;

import com.security.repositories.entities.enums.EstadoPrestamo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CrearPrestamoDTO {
    private Long codigo_libro;
    private String emailUsuario;
    private final Date prestamo = new Date();
    private Date devolucion;
    private EstadoPrestamo estado;

    public CrearPrestamoDTO(Long codigo_libro, String emailUsuario, Date devolucion){
        this.codigo_libro = codigo_libro;
        this.emailUsuario = emailUsuario;
        this.devolucion = devolucion;
    }
}
