package com.kush.prowler.service.impl;

import com.kush.prowler.model.SecurityUser;
import com.kush.prowler.model.entity.SystemUser;
import com.kush.prowler.repository.SystemUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author kush
 */
@RequiredArgsConstructor
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{

    private final SystemUserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SystemUser> systemUserOpt= repository.findByUsername(username);
        if(systemUserOpt.isPresent()){
            SystemUser user=systemUserOpt.get();
            user.setRoles(user.getRoles());
            return new SecurityUser(systemUserOpt.get());
        }
        else throw new UsernameNotFoundException(username);
    }

}