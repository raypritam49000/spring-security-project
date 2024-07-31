package com.security.rest.api.mappers;

import com.security.rest.api.dto.UserDTO;
import com.security.rest.api.mappers.base.BaseMapper;
import com.security.rest.api.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper extends BaseMapper<UserDTO, User> {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}