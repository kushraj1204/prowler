package com.kush.prowler.model.contract.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author kush
 */
@Data
public class SystemActionDto {
    private Long id;

    private String name;

    private String code;

    private String description;

    LocalDateTime createdAt;

    String createdBy;

    LocalDateTime lastModified;

    String lastModifiedBy;

    private boolean enabled;
}
