package com.security.rest.api.mappers;

import com.security.rest.api.dto.RoleDTO;
import com.security.rest.api.mappers.base.BaseMapper;
import com.security.rest.api.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper extends BaseMapper<RoleDTO, Role> {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
}