package com.testwork.authservice.Role;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link Role}
 */
public class RoleDto implements Serializable {
    @NotNull
    @Size(max = 50)
    private final Role name;

    public RoleDto(Role name) {
        this.name = name;
    }

    public Role getName() {
        return name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ")";
    }
}