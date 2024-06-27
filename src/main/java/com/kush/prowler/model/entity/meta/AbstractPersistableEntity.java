package com.kush.prowler.model.entity.meta;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author kush
 */
@MappedSuperclass
@Data
public abstract class AbstractPersistableEntity implements Serializable {

    @CreatedDate
    @Column(name = "created_at", updatable=false)
    LocalDateTime createdAt;

    @CreatedBy
    @AttributeOverride(name = "username", column = @Column(name = "created_by", updatable=false))
    @Embedded
    SystemUsername createdBy;

    @LastModifiedDate
    @Column(name = "last_modified_at",insertable=false)
    LocalDateTime lastModified;

    @LastModifiedBy
    @AttributeOverride(name = "username", column = @Column(name = "last_modified_by",insertable=false))
    @Embedded
    SystemUsername lastModifiedBy;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "deleted")
    private boolean deleted;

    @Version
    private Long version;

    public AbstractPersistableEntity() {
        this.enabled=true;
    }
}
