package com.kush.prowler.model;

import com.kush.prowler.model.entity.SystemRole;
import com.kush.prowler.model.entity.SystemUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class SecurityUser implements UserDetails {

    private final Collection<? extends GrantedAuthority> authorities;
    private Long id;
    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public SecurityUser(SystemUser systemUser){
        Set<SystemRole> roles=systemUser.getRoles();
        Set<String> actions=roles.stream()
                .flatMap(role -> role.getActions().stream())
                .collect(Collectors.toSet());
        Collection<SimpleGrantedAuthority> directAuthorities=actions.stream()
                .map(SimpleGrantedAuthority::new).toList();
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>(directAuthorities);
        this.id = systemUser.getId();
        this.username = systemUser.getUsername();
        this.password = systemUser.getPassword();
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = systemUser.isEnabled();
        this.authorities =   authorities;
    }


}