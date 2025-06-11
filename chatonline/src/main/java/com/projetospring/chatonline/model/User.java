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
import lombok.Value;

@Entity
@Table(name = "user_tb")
@Value
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final UUID id;

	@Column(nullable = false)
	private final String username;

	@Column(nullable = false, unique = true)
	private final String email;

	@Column(nullable = false)
	private final String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private final TypeUser type;

	@Column(name = "createAt")
	private final LocalDateTime createAt;

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

	UUID getId() {
		return id;
	}

	String getUsername() {
		return username;
	}

	String getEmail() {
		return email;
	}

	String getPassword() {
		return password;
	}

	TypeUser getType() {
		return type;
	}

}
