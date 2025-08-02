package com.projetospring.chatonline.core.security.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record LoginUserDto(@NotBlank @Max(value=20, message="User name cannot be longer than 20 characters") String username, @NotBlank String password) {

}
