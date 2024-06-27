package com.kush.prowler.model.entity;

import com.kush.prowler.model.entity.meta.AbstractPersistableEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author kush
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "prw_system_user")
public class SystemUser extends AbstractPersistableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "account_non_expired")
    private boolean accountNonExpired;

    @Column(name = "credential_non_expired")
    private boolean credentialNonExpired;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "prw_system_user_system_role")
    private Set<SystemRole> roles;

    public SystemUser(String username, String password, String email) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
