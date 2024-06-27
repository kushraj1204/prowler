package com.kush.prowler.service.impl;

import com.kush.prowler.exception.ServiceException;
import com.kush.prowler.mapper.SystemActionDtoMapper;
import com.kush.prowler.model.AppStatusCode;
import com.kush.prowler.model.contract.dto.SystemActionDto;
import com.kush.prowler.model.contract.request.action.SystemActionCreateRequest;
import com.kush.prowler.model.contract.request.action.SystemActionUpdateRequest;
import com.kush.prowler.model.entity.SystemAction;
import com.kush.prowler.repository.SystemActionRepository;
import com.kush.prowler.service.SystemActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author kush
 */
@RequiredArgsConstructor
@Service
@Transactional
public class SystemActionServiceImpl implements SystemActionService {

    private final SystemActionRepository repository;

    @Override
    public Page<SystemActionDto> getPage(Pageable pageable) {
        Page<SystemAction> actionPage = repository.findAll(pageable);
        return actionPage.map(SystemActionDtoMapper.MAPPER::systemActionToDto);
    }

    @Override
    public List<SystemActionDto> getAll() {
        return repository.findAll()
                .stream()
                .map(SystemActionDtoMapper.MAPPER::systemActionToDto)
                .toList();
    }

    @Override
    public SystemActionDto getById(Long id) {
        Optional<SystemAction> sysActionOpt=getSystemActionById(id);
        if(sysActionOpt.isPresent()){
            return SystemActionDtoMapper.MAPPER.systemActionToDto(sysActionOpt.get());
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "sys-action","id = "+id.toString());
        }
    }

    @Override
    public SystemActionDto create(SystemActionCreateRequest createRequest) {
        SystemAction action=SystemActionDtoMapper.MAPPER.actionCreateRequestToSystemAction(createRequest);
        SystemAction savedAction=repository.save(action);
        return SystemActionDtoMapper.MAPPER.systemActionToDto(savedAction);
    }

    @Override
    public SystemActionDto update(Long id, SystemActionUpdateRequest updateRequest) {
        Optional<SystemAction> sysActionOpt=getSystemActionById(id);
        if(sysActionOpt.isPresent()){
            SystemAction action=sysActionOpt.get();
            action.setName(updateRequest.getName());
            action.setCode(updateRequest.getCode());
            action.setDescription(updateRequest.getDescription());
            SystemAction savedAction=repository.save(action);
            return SystemActionDtoMapper.MAPPER.systemActionToDto(savedAction);
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "role");
        }
    }

    @Override
    public boolean delete(Long id) {
        if(Objects.nonNull(getById(id))){
            repository.deleteById(id);
        }
        return true;
    }

    @Override
    public SystemActionDto getByCode(String code) {
        Optional<SystemAction> sysActionOpt=getSystemActionByCode(code);
        if(sysActionOpt.isPresent()){
            return SystemActionDtoMapper.MAPPER.systemActionToDto(sysActionOpt.get());
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "sys-action","code = "+code);
        }
    }
    private Optional<SystemAction> getSystemActionByCode(String code){
        return repository.findByCodeIgnoreCase(code);
    }

    private Optional<SystemAction> getSystemActionById(Long id){
        return repository.findById(id);
    }
}
