package com.kush.prowler.service;

import com.kush.prowler.model.contract.dto.SystemActionDto;
import com.kush.prowler.model.contract.dto.SystemRoleDto;
import com.kush.prowler.model.contract.request.action.SystemActionCreateRequest;
import com.kush.prowler.model.contract.request.action.SystemActionUpdateRequest;
import com.kush.prowler.model.contract.request.role.SystemRoleCreateRequest;
import com.kush.prowler.model.contract.request.role.SystemRoleUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author kush
 */
public interface SystemRoleService {
    Page<SystemRoleDto> getPage(Pageable pageable);
    List<SystemRoleDto> getAll();
    SystemRoleDto getById(Long id);

    SystemRoleDto create(SystemRoleCreateRequest createRequest);

    SystemRoleDto update(Long id, SystemRoleUpdateRequest updateRequest);

    boolean delete(Long id);


}