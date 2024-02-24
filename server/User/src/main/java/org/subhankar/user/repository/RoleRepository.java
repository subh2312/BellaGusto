package org.subhankar.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.subhankar.user.model.DO.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
}
