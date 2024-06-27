package com.kush.prowler.model.contract.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author kush
 */
@Data
public class SystemUserDto {
    private Long id;

    private String username;

    private String email;

    private boolean enabled;

    LocalDateTime createdAt;

    String createdBy;

    LocalDateTime lastModified;

    String lastModifiedBy;

}
