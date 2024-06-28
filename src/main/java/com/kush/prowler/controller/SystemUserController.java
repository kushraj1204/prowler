package com.kush.prowler.controller;

import com.kush.prowler.model.AppStatusCode;
import com.kush.prowler.model.contract.dto.SystemUserDto;
import com.kush.prowler.model.contract.request.user.SystemUserCreateRequest;
import com.kush.prowler.model.contract.request.user.SystemUserUpdateRequest;
import com.kush.prowler.model.contract.response.common.ApiResponse;
import com.kush.prowler.service.MessagingService;
import com.kush.prowler.service.SystemUserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/system-user")
@CrossOrigin
@Slf4j
public class SystemUserController {
    private final SystemUserService service;

    private final MessagingService messagingService;

    public SystemUserController(SystemUserService service, MessagingService messagingService) {
        this.service = service;
        this.messagingService = messagingService;
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<SystemUserDto>>> getAll() {
        List<SystemUserDto> response= service.getAll();
        ApiResponse<List<SystemUserDto>> apiResponse =  new ApiResponse<List<SystemUserDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001,new String[]{"sys-user"}));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<Page<SystemUserDto>>> getPage(Pageable pageableReq) {
        Pageable pageable = PageRequest.of(pageableReq.getPageNumber()>0? pageableReq.getPageNumber()-1 : 0,
                pageableReq.getPageSize() ,
                pageableReq.getSort());
        Page<SystemUserDto> response= service.getPage(pageable);
        ApiResponse<Page<SystemUserDto>> apiResponse =  new ApiResponse<Page<SystemUserDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001,new String[]{"sys-user"}));
        return ResponseEntity.ok(apiResponse);

    }
    @PostMapping("")
    public ResponseEntity<ApiResponse<SystemUserDto>> create(@Valid @RequestBody SystemUserCreateRequest createRequest) {
        SystemUserDto response= service.create(createRequest);
        ApiResponse<SystemUserDto> apiResponse =  new ApiResponse<SystemUserDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20002,new String[]{"sys-user"}));
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SystemUserDto>> getCourse(@PathVariable Long id) {
        SystemUserDto response= service.getById(id);
        ApiResponse<SystemUserDto> apiResponse =  new ApiResponse<SystemUserDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20003,new String[]{"sys-user"}));
        return ResponseEntity.ok(apiResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SystemUserDto>> update(@Valid @RequestBody SystemUserUpdateRequest updateRequest,
                                                              @PathVariable Long id) {
        SystemUserDto response= service.update(id,updateRequest);
        ApiResponse<SystemUserDto> apiResponse =  new ApiResponse<SystemUserDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20004,new String[]{"sys-user"}));
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable Long id) {
        boolean response= service.delete(id);
        ApiResponse<Boolean> apiResponse =  new ApiResponse<Boolean>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20005,new String[]{"sys-user"}));
        return ResponseEntity.ok(apiResponse);
    }

}
