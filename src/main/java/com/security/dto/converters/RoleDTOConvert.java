package com.security.dto.converters;

import com.security.dto.RoleDTO;
import com.security.repositories.entities.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RoleDTOConvert {
    
    private ModelMapper mapper;

    public RoleDTO convertDTO(Role role) {
        RoleDTO roleDTO = mapper.map(role, RoleDTO.class);
        return roleDTO;
    }

    public Role convertEntity(RoleDTO dto) {
        Role role = mapper.map(dto, Role.class);
        return role;
    }
}
