package com.kush.prowler.model.contract.response.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponse {
    private String username;
    private String accessToken;
    private String refreshToken;
    private int accessTokenExpiry;
    private int refreshTokenExpiry;

}
