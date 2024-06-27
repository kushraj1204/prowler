package com.kush.prowler.model.contract.request.action;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @author kush
 */
@Data
public class SystemActionCreateRequest {

    @NotBlank
    @NotEmpty
    private String name;

    @NotBlank
    @NotEmpty
    private String code;

    private String description;

}
