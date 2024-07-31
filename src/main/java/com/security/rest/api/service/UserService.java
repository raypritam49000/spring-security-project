package com.security.rest.api.service;

import com.security.rest.api.dto.UserDTO;
import com.security.rest.api.dto.UserRequestDTO;

public interface UserService {
    public UserDTO registerUser(UserRequestDTO userDto);
}
