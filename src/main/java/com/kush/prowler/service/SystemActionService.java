package com.kush.prowler.service;

import com.kush.prowler.model.contract.dto.SystemActionDto;
import com.kush.prowler.model.contract.request.action.SystemActionCreateRequest;
import com.kush.prowler.model.contract.request.action.SystemActionUpdateRequest;
import com.kush.prowler.model.entity.SystemAction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author kush
 */
public interface SystemActionService {
    Page<SystemActionDto> getPage(Pageable pageable);
    List<SystemActionDto> getAll();
    SystemActionDto getById(Long id);

    SystemActionDto create(SystemActionCreateRequest createRequest);

    SystemActionDto update(Long id, SystemActionUpdateRequest updateRequest);

    boolean delete(Long id);


    SystemActionDto getByCode(String code);
}
