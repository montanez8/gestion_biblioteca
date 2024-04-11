package com.security.dto.converters;

import com.security.dto.RoleDTO;
import com.security.dto.UserDTO;
import com.security.repositories.entities.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.security.repositories.entities.User;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserDTOConvert {
    
    private ModelMapper mapper;
    private RoleDTOConvert roleDTOConvert;

    public UserDTO convertDTO(User user) {
        UserDTO dto = mapper.map(user, UserDTO.class);
        for (Role role : user.getRoles()){
            dto.getRoles().add(roleDTOConvert.convertDTO(role));
        }
        return dto;
    }

    public User convertEntity(UserDTO user) {
        User entity = mapper.map(user, User.class);
        for (RoleDTO role : user.getRoles()){
            entity.getRoles().add(roleDTOConvert.convertEntity(role));
        }
        return mapper.map(user, User.class);
    }
}
