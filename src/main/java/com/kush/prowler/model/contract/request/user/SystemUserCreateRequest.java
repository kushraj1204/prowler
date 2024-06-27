package com.kush.prowler.model.contract.request.user;

import lombok.Data;

/**
 * @author kush
 */
@Data
public class SystemUserCreateRequest {

    private String username;

    private String email;

    private String password;

    private boolean enabled;

}
