package com.kush.prowler.mapper;

import com.kush.prowler.model.contract.dto.SystemActionDto;
import com.kush.prowler.model.contract.request.action.SystemActionCreateRequest;
import com.kush.prowler.model.contract.request.action.SystemActionUpdateRequest;
import com.kush.prowler.model.entity.SystemAction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author kush
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SystemActionDtoMapper {
    SystemActionDtoMapper MAPPER =
            Mappers.getMapper(SystemActionDtoMapper.class);

    SystemAction dtoToEntity(SystemActionCreateRequest request);
    SystemAction dtoToEntity(SystemActionUpdateRequest request);
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    SystemActionDto entityToDto(SystemAction action);


}