package com.testwork.authservice.user;

import com.testwork.authservice.Role.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserCredential {
    private String email;
    private String password;
    private List<Role> roles = new ArrayList<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public List<String> getRolesStringed() {
        return roles.stream().map(Role::getAuthority).collect(Collectors.toList());
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}