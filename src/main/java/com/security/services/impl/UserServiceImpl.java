package com.security.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.security.dto.RoleDTO;
import com.security.dto.UserDTO;
import com.security.repositories.entities.Role;
import org.springframework.stereotype.Service;

import com.security.dto.converters.RoleDTOConvert;
import com.security.dto.converters.UserDTOConvert;
import com.security.repositories.PrestamoRepository;
import com.security.repositories.RepositoryRole;
import com.security.repositories.RepositoryUser;
import com.security.repositories.entities.User;
import com.security.services.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private RepositoryUser repositoryUser;
    private RepositoryRole repositoryRole;
    private PrestamoRepository prestamoRepository;
    private UserDTOConvert userDTOConverter;
    private RoleDTOConvert roleDTOConvert;

    @SuppressWarnings("null")
    @Override
    public UserDTO save(UserDTO user) {
        Optional<User> userEntity = repositoryUser.findByUsername(user.getUsername());
        if (userEntity.isEmpty()) {
            User entity = userDTOConverter.convertEntity(user);
            for (Role role : entity.getRoles()){
                Role saved = repositoryRole.save(role);
                role.setId(saved.getId());
            }
            User userSaved = repositoryUser.save(entity);
            return userDTOConverter.convertDTO(userSaved);
        }
        return null;
    }

    @Override
    public UserDTO findByEmail(String email) {
        Optional<User> userEntity = repositoryUser.findByUsername(email);
        if (userEntity.isPresent()) {
            return userDTOConverter.convertDTO(userEntity.get());
        }
        return null;
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> entities = (List<User>) repositoryUser.findAll();
        List<UserDTO> dtos = new ArrayList<>();
        for (User entity : entities) {
            dtos.add(userDTOConverter.convertDTO(entity));
        }
        return dtos;
    }

    @Override
    public boolean edit(String email, UserDTO user) {
        Optional<User> userEntity = repositoryUser.findByUsername(email);
        if (userEntity.isPresent()) {
            User entity = userEntity.get();
            entity.setUsername(user.getUsername());
            entity.setPassword(user.getPassword());
            for (RoleDTO role : user.getRoles()) {
                entity.getRoles().add(roleDTOConvert.convertEntity(role));
            }
            repositoryUser.save(entity);
            return true;
        }
        return false;
    }

    @SuppressWarnings("null")
    @Override
    public boolean deleteByEmail(String email) {
        Optional<User> userEntity = repositoryUser.findByUsername(email);
        if (userEntity.isPresent()) {
            if (prestamoRepository.existsByUsuario(userEntity.get())) {
                return false;
            }
            repositoryUser.delete(userEntity.get());
            return true;
        }
        return false;
    }
}
