package com.testwork.authservice.user;

import com.testwork.authservice.Role.Role;
import com.testwork.authservice.Role.RoleDto;

import java.util.stream.Collectors;

public class Mapper {
    public static UserCredential toUserCredential(UserCredentialDto userCredentialDto) {
        UserCredential userCredential = new UserCredential();
        userCredential.setEmail(userCredentialDto.getEmail());
        userCredential.setPassword(userCredentialDto.getPassword());
        userCredential.setRoles(
                userCredentialDto.getRoles().stream().map(Mapper::toRole).collect(Collectors.toList())
        );
        return userCredential;
    }

    private static Role toRole(RoleDto roleDto) {
        return roleDto.getName();
    }
}
