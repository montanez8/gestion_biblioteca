package com.security.repositories;

import java.math.BigInteger;

import com.security.repositories.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryRole extends CrudRepository<Role, Long>{
    
}
