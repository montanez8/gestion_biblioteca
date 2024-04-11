package com.security.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.security.dto.CrearPrestamoDTO;
import com.security.dto.ListarPrestamoDTO;
import com.security.repositories.entities.Libro;
import com.security.repositories.entities.Prestamo;
import org.springframework.stereotype.Service;

import com.security.dto.CambiarPrestamoDTO;
import com.security.dto.converters.CambiarPrestamoDTOConvert;
import com.security.dto.converters.CrearPrestamoDTOConvert;
import com.security.dto.converters.ListarPrestamoDTOConvert;
import com.security.repositories.LibroRepository;
import com.security.repositories.PrestamoRepository;
import com.security.services.PrestamoService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PrestamoServiceImpl implements PrestamoService{

    private PrestamoRepository prestamoRepository;
    private LibroRepository libroRepository;
    private CrearPrestamoDTOConvert crearPrestamoDTO;
    private CambiarPrestamoDTOConvert cambiarPrestamoDTO;
    private ListarPrestamoDTOConvert listarPrestamoDTO;

    @Override
    public CrearPrestamoDTO crear(CrearPrestamoDTO crearPrestamo) {
        CrearPrestamoDTO crearDTO = new CrearPrestamoDTO();
        Prestamo prestamo = crearPrestamoDTO.convertEntity(crearPrestamo);
        if (prestamo != null && prestamo.getLibro().existeInventario()) {
            crearDTO = crearPrestamoDTO.convertDTO(prestamoRepository.save(prestamo));
            Libro libro = prestamo.getLibro();
            libro.libroPrestado();
            libroRepository.save(libro);
            return crearDTO;
        }
        return crearDTO;
    }

    @Override
    public List<ListarPrestamoDTO> listar() {
        List<Prestamo> prestamos = (List<Prestamo>) prestamoRepository.findAll();
        List<ListarPrestamoDTO> prestamoDTOs = new ArrayList<>();
        for (Prestamo prestamo : prestamos){
            prestamoDTOs.add(listarPrestamoDTO.convertDTO(prestamo));
        }
        return prestamoDTOs;
    }

    @Override
    public Prestamo editar(CambiarPrestamoDTO prestamo) {
        Prestamo prestamoEdit = cambiarPrestamoDTO.convertEntity(prestamo);
        if (prestamoEdit.getId() != null) {
            return prestamoRepository.save(prestamoEdit);
        }
        return prestamoEdit;
    }    
}
