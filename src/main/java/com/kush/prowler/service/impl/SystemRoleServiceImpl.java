package com.kush.prowler.service.impl;

import com.kush.prowler.exception.ServiceException;
import com.kush.prowler.mapper.SystemRoleDtoMapper;
import com.kush.prowler.model.AppStatusCode;
import com.kush.prowler.model.contract.dto.SystemRoleDto;
import com.kush.prowler.model.contract.request.role.SystemRoleCreateRequest;
import com.kush.prowler.model.contract.request.role.SystemRoleUpdateRequest;
import com.kush.prowler.model.entity.SystemRole;
import com.kush.prowler.repository.SystemRoleRepository;
import com.kush.prowler.service.SystemRoleService;
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
public class SystemRoleServiceImpl implements SystemRoleService {

    private final SystemRoleRepository repository;

    @Override
    public Page<SystemRoleDto> getPage(Pageable pageable) {
        Page<SystemRole> page = repository.findAll(pageable);
        return page.map(SystemRoleDtoMapper.MAPPER::entityToDto);
    }

    @Override
    public List<SystemRoleDto> getAll() {
        return repository.findAll()
                .stream()
                .map(SystemRoleDtoMapper.MAPPER::entityToDto)
                .toList();
    }

    @Override
    public SystemRoleDto getById(Long id) {
        Optional<SystemRole> sysRoleOpt= getSystemRoleById(id);
        if(sysRoleOpt.isPresent()){
            return SystemRoleDtoMapper.MAPPER.entityToDto(sysRoleOpt.get());
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "role",id.toString());
        }
    }

    @Override
    public SystemRoleDto create(SystemRoleCreateRequest createRequest) {
        SystemRole role=SystemRoleDtoMapper.MAPPER.dtoToEntity(createRequest);
        SystemRole savedRole=repository.save(role);
        return SystemRoleDtoMapper.MAPPER.entityToDto(savedRole);
    }

    @Override
    public SystemRoleDto update(Long id, SystemRoleUpdateRequest updateRequest) {
        Optional<SystemRole> sysRoleOpt= getSystemRoleById(id);
        if(sysRoleOpt.isPresent()){
            SystemRole role=sysRoleOpt.get();
            role.setName(updateRequest.getName());
            role.setCode(updateRequest.getCode());
            role.setDescription(updateRequest.getDescription());
            role.setActions(updateRequest.getActions());
            SystemRole savedRole=repository.save(role);
            return SystemRoleDtoMapper.MAPPER.entityToDto(savedRole);
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

    private Optional<SystemRole> getSystemRoleById(Long id){
        return repository.findById(id);
    }
}
