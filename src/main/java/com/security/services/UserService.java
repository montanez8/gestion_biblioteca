package com.security.services;

import java.util.List;

import com.security.dto.UserDTO;

public interface UserService {
    UserDTO save(UserDTO user);
    UserDTO findByEmail(String email);
    List<UserDTO> findAll();
    boolean edit(String email, UserDTO user);
    boolean deleteByEmail(String email);
}
