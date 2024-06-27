package com.kush.prowler.service.impl;

import com.kush.prowler.exception.ServiceException;
import com.kush.prowler.mapper.SystemUserDtoMapper;
import com.kush.prowler.model.AppStatusCode;
import com.kush.prowler.model.contract.dto.SystemUserDto;
import com.kush.prowler.model.contract.request.user.SystemUserCreateRequest;
import com.kush.prowler.model.contract.request.user.SystemUserUpdateRequest;
import com.kush.prowler.model.entity.SystemRole;
import com.kush.prowler.model.entity.SystemUser;
import com.kush.prowler.repository.SystemUserRepository;
import com.kush.prowler.service.SystemUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author kush
 */
@Service
@RequiredArgsConstructor
@Transactional
public class SystemUserServiceImpl implements UserDetailsService, SystemUserService {

    private final SystemUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SystemUser> systemUserOpt= repository.findByUsername(username);
        if(systemUserOpt.isPresent()){
            Set<SystemRole> roles=systemUserOpt.get().getRoles();
            Set<String> actions=roles.stream()
                    .flatMap(role -> role.getActions().stream())
                    .collect(Collectors.toSet());
            Collection<SimpleGrantedAuthority> directAuthorities=actions.stream()
                    .map(SimpleGrantedAuthority::new).toList();
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>(directAuthorities);
            return new User(systemUserOpt.get().getUsername(),
                    systemUserOpt.get().getPassword(),
                    systemUserOpt.get().isEnabled(),
                    systemUserOpt.get().isAccountNonExpired(),
                    systemUserOpt.get().isCredentialNonExpired(),
                    systemUserOpt.get().isAccountNonLocked(), authorities);
        }
        else throw new UsernameNotFoundException(username);
    }


    @Override
    public Page<SystemUserDto> getPage(Pageable pageable) {
        Page<SystemUser> page = repository.findAll(pageable);
        return page.map(SystemUserDtoMapper.MAPPER::systemUserToDto);
    }

    @Override
    public List<SystemUserDto> getAll() {
        return repository.findAll()
                .stream()
                .map(SystemUserDtoMapper.MAPPER::systemUserToDto)
                .toList();
    }

    @Override
    public SystemUserDto getById(Long id) {
        Optional<SystemUser> sysUserOpt= getSystemUserById(id);
        if(sysUserOpt.isPresent()){
            return SystemUserDtoMapper.MAPPER.systemUserToDto(sysUserOpt.get());
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "user");
        }
    }

    @Override
    public SystemUserDto create(SystemUserCreateRequest createRequest) {
        SystemUser user=SystemUserDtoMapper.MAPPER.createRequestToSystemUser(createRequest);
        SystemUser savedUser=repository.save(user);
        return SystemUserDtoMapper.MAPPER.systemUserToDto(savedUser);
    }

    @Override
    public SystemUserDto update(Long id, SystemUserUpdateRequest updateRequest) {
        Optional<SystemUser> sysUserOpt= getSystemUserById(id);
        if(sysUserOpt.isPresent()){
            SystemUser user=sysUserOpt.get();
            user.setUsername(updateRequest.getUsername());
            user.setEmail(updateRequest.getEmail());
            user.setEnabled(updateRequest.isEnabled());
            SystemUser savedUser=repository.save(user);
            return SystemUserDtoMapper.MAPPER.systemUserToDto(savedUser);
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "user");
        }
    }

    @Override
    public boolean delete(Long id) {
        if(Objects.nonNull(getById(id))){
            repository.deleteById(id);
        }
        return true;
    }

    private Optional<SystemUser> getSystemUserById(Long id){
        return repository.findById(id);
    }
}
