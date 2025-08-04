package com.projetospring.chatonline.infrastructure.security;

import java.util.Collection;
import java.util.List;

import com.projetospring.chatonline.modules.user.domain.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.projetospring.chatonline.modules.user.domain.User;

public final class UserDetailsImpl implements UserDetails {

	private final UserEntity user;

	public UserDetailsImpl(UserEntity user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(user.getType());
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public UserEntity getUser() {
		return this.user;
	}
}
