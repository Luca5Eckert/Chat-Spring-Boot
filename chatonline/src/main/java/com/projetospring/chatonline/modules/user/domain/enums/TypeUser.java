package com.projetospring.chatonline.modules.user.domain.enums;

import org.springframework.security.core.GrantedAuthority;

public enum TypeUser implements GrantedAuthority {
	ADMIN("ROLE_ADMIN"), USER("ROLE_USER"), GUEST("ROLE_GUEST");

	private final String authority;

	TypeUser(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}
}