package com.kush.prowler.model.entity;

import com.kush.prowler.model.entity.meta.AbstractPersistableEntity;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.Set;

/**
 * @author kush
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "prw_system_role")
public class SystemRole extends AbstractPersistableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "description")
    private String description;

    @Type(JsonBinaryType.class)
    @Column(name = "actions", columnDefinition = "jsonb")
    private Set<String> actions;

    public SystemRole(String name, String code, String description, Set<String> actions) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.actions = actions;
    }
}