package com.security.repositories.entities;

import java.util.Date;
import com.security.repositories.entities.enums.EstadoPrestamo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prestamos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "libros_id")
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User usuario;

    @Column(nullable = false)
    private Date prestamo;

    @Column(nullable = false)
    private Date devolucion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPrestamo estado;
}
