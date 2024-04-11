package com.security.controllers;

import java.util.List;

import com.security.dto.UserDTO;
import com.security.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;


@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    
    @PostMapping("/")
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO) {
        UserDTO user = userService.save(userDTO);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> listar() {
        List<UserDTO> list = userService.findAll();
        if (!list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> findByEmail(@PathVariable String email) {
        UserDTO user = userService.findByEmail(email);
        if (user!= null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{email}")
    public ResponseEntity<UserDTO> edit(@PathVariable String email, @RequestBody UserDTO userDTO) {
        if (userService.edit(email, userDTO)) {
            return new ResponseEntity<>(userService.findByEmail(email), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteByEmail(@PathVariable String email) {
        if (userService.deleteByEmail(email)) {
            return new ResponseEntity<>("Usuario eliminado correctamente", HttpStatus.OK);
        }
        return new ResponseEntity<>("El usuario tiene prestamos o no existe", HttpStatus.BAD_REQUEST);
    }
}
