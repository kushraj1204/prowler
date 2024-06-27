package com.kush.prowler.controller;

import com.kush.prowler.model.AppStatusCode;
import com.kush.prowler.model.contract.dto.SystemActionDto;
import com.kush.prowler.model.contract.request.action.SystemActionCreateRequest;
import com.kush.prowler.model.contract.request.action.SystemActionUpdateRequest;
import com.kush.prowler.model.contract.response.common.ApiResponse;
import com.kush.prowler.service.MessagingService;
import com.kush.prowler.service.SystemActionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/system-action")
@CrossOrigin
@Slf4j
public class SystemActionController {
    private final SystemActionService service;

    private final MessagingService messagingService;

    public SystemActionController(SystemActionService service, MessagingService messagingService) {
        this.service = service;
        this.messagingService = messagingService;
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<SystemActionDto>>> getAll() {
        List<SystemActionDto> response= service.getAll();
        ApiResponse<List<SystemActionDto>> apiResponse =  new ApiResponse<List<SystemActionDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001.getDescription(),new String[]{"sys-action"}));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<Page<SystemActionDto>>> getPage(Pageable pageableReq) {
        Pageable pageable = PageRequest.of(pageableReq.getPageNumber()>0? pageableReq.getPageNumber()-1 : 0,
                pageableReq.getPageSize() ,
                pageableReq.getSort());
        Page<SystemActionDto> response= service.getPage(pageable);
        ApiResponse<Page<SystemActionDto>> apiResponse =  new ApiResponse<Page<SystemActionDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001.getDescription(),new String[]{"sys-action"}));
        return ResponseEntity.ok(apiResponse);

    }
    @PostMapping("")
    public ResponseEntity<ApiResponse<SystemActionDto>> create(@Valid @RequestBody SystemActionCreateRequest createRequest) {
        SystemActionDto response= service.create(createRequest);
        ApiResponse<SystemActionDto> apiResponse =  new ApiResponse<SystemActionDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20002.getDescription(),new String[]{"sys-action"}));
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SystemActionDto>> getCourse(@PathVariable Long id) {
        SystemActionDto response= service.getById(id);
        ApiResponse<SystemActionDto> apiResponse =  new ApiResponse<SystemActionDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20003.getDescription(),new String[]{"sys-action"}));
        return ResponseEntity.ok(apiResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SystemActionDto>> update(@Valid @RequestBody SystemActionUpdateRequest updateRequest,
                                                              @PathVariable Long id) {
        SystemActionDto response= service.update(id,updateRequest);
        ApiResponse<SystemActionDto> apiResponse =  new ApiResponse<SystemActionDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20004.getDescription(),new String[]{"sys-action"}));
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable Long id) {
        boolean response= service.delete(id);
        ApiResponse<Boolean> apiResponse =  new ApiResponse<Boolean>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20005.getDescription(),new String[]{"sys-action"}));
        return ResponseEntity.ok(apiResponse);
    }

}
