package com.projetospring.chatonline.modules.user.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import com.projetospring.chatonline.modules.user.domain.enums.TypeUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_tb")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false)
	private TypeUser type;

	@Column(name = "createAt", nullable = false, updatable = false)
	@org.hibernate.annotations.CreationTimestamp
	private LocalDateTime createAt;

	private User(String username, String email, String password, TypeUser type) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.type = type;
	}

	public static User createUser(String username, String email, String password, TypeUser type) {
		return new User(username, email, password, type);
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(@NotBlank String email) {
		this.email = email;
	}

	public UUID getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setPassword(@NotBlank String password) {
		this.password = password;
	}

	public void setType(@NotNull TypeUser type) {
		this.type = type;
	}

	public TypeUser getType() {
		return this.type;
	}

	public String getPassword() {
		return this.password;
	}

	public String getUsername() {
		return this.username;
	}
}
