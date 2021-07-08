package com.example.springshop1.repositories;


import com.example.springshop1.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository  extends JpaRepository<Role, Long>  {
    Optional<Role> findOneByName(String role_client);
}
