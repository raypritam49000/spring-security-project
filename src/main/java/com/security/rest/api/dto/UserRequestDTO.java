package com.security.rest.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Collection<String> roles;
    private Collection<String> privileges;
}
