package com.kush.prowler.mapper;

import com.kush.prowler.model.contract.dto.SystemRoleDto;
import com.kush.prowler.model.contract.request.role.SystemRoleCreateRequest;
import com.kush.prowler.model.contract.request.role.SystemRoleUpdateRequest;
import com.kush.prowler.model.entity.SystemRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author kush
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SystemRoleDtoMapper {
    SystemRoleDtoMapper MAPPER =
            Mappers.getMapper(SystemRoleDtoMapper.class);

    SystemRole dtoToEntity(SystemRoleCreateRequest request);
    SystemRole dtoToEntity(SystemRoleUpdateRequest request);
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    SystemRoleDto entityToDto(SystemRole role);


}