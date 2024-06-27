package com.kush.prowler.model.contract.request.user;

import lombok.Data;

/**
 * @author kush
 */
@Data
public class SystemUserUpdateRequest {

    private String username;

    private String email;
    private boolean enabled;
}
