package com.srikanth.brewery.repositories.security;

import com.srikanth.brewery.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
