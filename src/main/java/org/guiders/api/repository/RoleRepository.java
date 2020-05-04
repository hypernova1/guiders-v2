package org.guiders.api.repository;

import org.guiders.api.constant.RoleName;
import org.guiders.api.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName role);
}
