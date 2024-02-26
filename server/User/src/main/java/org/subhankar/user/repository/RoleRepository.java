package org.subhankar.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.subhankar.user.model.DO.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(String name);
}
