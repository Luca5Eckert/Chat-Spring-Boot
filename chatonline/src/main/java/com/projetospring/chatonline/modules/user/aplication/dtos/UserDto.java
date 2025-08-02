package com.projetospring.chatonline.modules.user.aplication.dtos;

import com.projetospring.chatonline.model.User;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record UserDto(@NotBlank @Max(value=20, message="User name cannot be longer than 20 characters") String name, @NotBlank String email) {

	public UserDto(User user) {
		this(user.getUsername(), user.getEmail());
	}
}
