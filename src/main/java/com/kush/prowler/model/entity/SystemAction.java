package com.kush.prowler.model.entity;

import com.kush.prowler.model.entity.meta.AbstractPersistableEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author kush
 */

@Data
@Entity
@NoArgsConstructor
@Table(name = "prw_system_action")
public class SystemAction  extends AbstractPersistableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "description")
    private String description;

    public SystemAction(String name, String code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
    }
}
