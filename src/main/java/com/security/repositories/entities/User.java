package com.security.repositories.entities;

import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    @NotBlank
    @Size(min = 4, max = 20)
    private String username;

    @Column(nullable = false)
    @NotBlank
    private String password;
    @Getter
    @Value("${true}")
    private boolean enabled = true;
    @ManyToMany
    @JoinTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"), uniqueConstraints = {
           @UniqueConstraint(columnNames = {"usuario_id", "rol_id"})})
    private List<Role> roles;


    @Getter
    @Transient
    private boolean admin;

}

