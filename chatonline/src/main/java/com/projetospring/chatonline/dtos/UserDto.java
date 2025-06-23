package com.projetospring.chatonline.dtos;

import com.projetospring.chatonline.model.User;

public record UserDto(String name, String email) {

	public UserDto(User user) {
		this(user.getUsername(), user.getEmail());
	}
}
