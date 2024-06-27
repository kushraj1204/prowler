package com.kush.prowler.model.contract.request.role;

import lombok.Data;

import java.util.Set;

/**
 * @author kush
 */
@Data
public class SystemRoleCreateRequest {


    private String name;

    private String code;

    private String description;

    private Set<String> actions;

}
