package com.testwork.authservice.user;

import com.testwork.authservice.Role.RoleDto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * DTO for {@link UserCredential}
 */
public class UserCredentialDto implements Serializable {
    private final String email;
    private final String password;
    private final String matchPassword;
    private final Set<RoleDto> roles = new HashSet<>();

    public UserCredentialDto(
            String email,
            String password,
            String matchPassword,
            Set<RoleDto> roles
    ) {
        this.email = email;
        this.password = password;
        this.matchPassword = matchPassword;
        if (roles != null) {
            this.roles.addAll(roles);
        }
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getMatchPassword() {
        return matchPassword;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "email = " + email + ", " +
                "password = " + password + ", " +
                "roles = " + roles + ")";
    }
}