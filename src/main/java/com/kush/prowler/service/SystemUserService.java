package com.kush.prowler.service;

import com.kush.prowler.model.contract.dto.SystemRoleDto;
import com.kush.prowler.model.contract.dto.SystemUserDto;
import com.kush.prowler.model.contract.request.user.SystemUserCreateRequest;
import com.kush.prowler.model.contract.request.user.SystemUserUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author kush
 */
public interface SystemUserService {
    Page<SystemUserDto> getPage(Pageable pageable);
    List<SystemUserDto> getAll();
    SystemUserDto getById(Long id);

    SystemUserDto create(SystemUserCreateRequest createRequest);

    SystemUserDto update(Long id, SystemUserUpdateRequest updateRequest);

    boolean delete(Long id);


}