package com.kush.prowler.repository;

import com.kush.prowler.model.entity.SystemAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author kush
 */
public interface SystemActionRepository extends JpaRepository<SystemAction, Long> {
    Optional<SystemAction> findByCodeIgnoreCase(String code);
}
