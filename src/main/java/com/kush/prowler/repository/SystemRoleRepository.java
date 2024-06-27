package com.kush.prowler.repository;

import com.kush.prowler.model.entity.SystemRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author kush
 */
public interface SystemRoleRepository extends JpaRepository<SystemRole, Long> {
    Optional<SystemRole> findByCodeIgnoreCase(String roleCode);
}
