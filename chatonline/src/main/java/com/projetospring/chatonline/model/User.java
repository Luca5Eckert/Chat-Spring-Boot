package com.projetospring.chatonline.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Entity
@Table(name = "user_tb")
@Value
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final UUID id;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false, unique= true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private TypeUser type;

	@Column(name = "createAt")
	private final LocalDateTime createAt;

	private User() {
		this.id = null;
		this.username = null;
		this.email = null;
		this.password = null;
		this.type = null;
		this.createAt = null;
	}

	private User(UUID id, String username, String email, String password, TypeUser type, LocalDateTime createAt) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.type = type;
		this.createAt = createAt;
	}

	public static User createUser(UUID id, String name, String email, String password, TypeUser type,
			LocalDateTime createAt) {
		return new User(id, name, email, password, type, createAt);
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(@NotBlank String email) {
		this.email = email;
	}

	public void setPassword(@NotBlank String password) {
		this.password = password;
	}

	public void setType(@NotNull TypeUser type) {
		this.type = type;
	}

	UUID getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	TypeUser getType() {
		return type;
	}

}
