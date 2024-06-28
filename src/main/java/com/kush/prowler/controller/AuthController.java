package com.kush.prowler.controller;

import com.kush.prowler.model.AppStatusCode;
import com.kush.prowler.model.contract.request.auth.AuthenticationRequest;
import com.kush.prowler.model.contract.request.auth.RefreshTokenRequest;
import com.kush.prowler.model.contract.response.common.ApiResponse;
import com.kush.prowler.model.contract.response.common.AuthenticationResponse;
import com.kush.prowler.service.AuthService;
import com.kush.prowler.service.MessagingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService service;

    private final MessagingService messagingService;


    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> authenticate(@Valid @RequestBody AuthenticationRequest data) {


        AuthenticationResponse response = service.authenticate(new UsernamePasswordAuthenticationToken
                    (data.getUsername(), data.getPassword()));
        ApiResponse<AuthenticationResponse> apiResponse =  new ApiResponse<AuthenticationResponse>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20007));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/sign-in/refresh-token")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> signInFromRefreshToken(@Valid @RequestBody RefreshTokenRequest data) {


        AuthenticationResponse response = service.authenticateRefreshToken(data);
        ApiResponse<AuthenticationResponse> apiResponse =  new ApiResponse<AuthenticationResponse>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20007));
        return ResponseEntity.ok(apiResponse);
    }
}
