package com.projetospring.chatonline.model;

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
	private final String name;

	@Column(nullable = false, unique = true)
	private final String email;

	@Column(nullable = false)
	private final String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private final TypeUser type;

	private User() {
		this.id = null;
		this.name = "";
		this.email = "";
		this.password = "";
		this.type = null;
	}

	private User(UUID id, String name, String email, String password, TypeUser type) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.type = type;
	}

	public static User createUser(UUID id, String name, String email, String password, TypeUser type) {
		return new User(id, name, email, password, type);
	}

	UUID getId() {
		return id;
	}

	String getName() {
		return name;
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
