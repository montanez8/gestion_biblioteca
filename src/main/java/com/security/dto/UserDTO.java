package com.security.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;
    private List<RoleDTO> roles;


}
