package com.security.services;

import java.util.List;

import com.security.dto.CrearPrestamoDTO;
import com.security.dto.ListarPrestamoDTO;
import com.security.dto.CambiarPrestamoDTO;
import com.security.repositories.entities.Prestamo;

public interface PrestamoService {
    CrearPrestamoDTO crear(CrearPrestamoDTO crearPrestamo);
    List<ListarPrestamoDTO> listar();
    Prestamo editar(CambiarPrestamoDTO prestamo);
}
