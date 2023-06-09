package com.hans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hans.model.ERole;
import com.hans.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
	Optional<Role> findByRoleName(ERole roleName);

}
