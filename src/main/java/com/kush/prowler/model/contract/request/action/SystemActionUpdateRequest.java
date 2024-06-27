package com.kush.prowler.model.contract.request.action;

import lombok.Data;

/**
 * @author kush
 */
@Data
public class SystemActionUpdateRequest {

    private String name;

    private String code;

    private String description;
}
