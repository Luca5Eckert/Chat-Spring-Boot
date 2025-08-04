package com.projetospring.chatonline.modules.user.aplication.dtos;

import com.projetospring.chatonline.modules.user.domain.UserEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record UserDto(@NotBlank @Max(value=20, message="User name cannot be longer than 20 characters") String name, @NotBlank String email) {

	public UserDto(UserEntity user) {
		this(user.getUsername(), user.getEmail());
	}
}
