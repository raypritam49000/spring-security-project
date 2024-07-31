package com.security.rest.api.mappers;

import com.security.rest.api.dto.PrivilegeDTO;
import com.security.rest.api.mappers.base.BaseMapper;
import com.security.rest.api.model.Privilege;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PrivilegeMapper extends BaseMapper<PrivilegeDTO, Privilege> {
    PrivilegeMapper INSTANCE = Mappers.getMapper(PrivilegeMapper.class);
}
