package com.security.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.security.repositories.entities.User;

public interface RepositoryUser extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> deleteByEmail(String email);

}
