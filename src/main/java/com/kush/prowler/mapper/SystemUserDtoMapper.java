package com.kush.prowler.mapper;

import com.kush.prowler.model.contract.dto.SystemUserDto;
import com.kush.prowler.model.contract.request.user.SystemUserCreateRequest;
import com.kush.prowler.model.contract.request.user.SystemUserUpdateRequest;
import com.kush.prowler.model.entity.SystemUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author kush
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SystemUserDtoMapper {
    SystemUserDtoMapper MAPPER =
            Mappers.getMapper(SystemUserDtoMapper.class);

    SystemUser dtoToEntity(SystemUserCreateRequest request);
    SystemUser dtoToEntity(SystemUserUpdateRequest request);
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    SystemUserDto entityToDto(SystemUser user);


}