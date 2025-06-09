package com.projetospring.chatonline.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_tb")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final UUID id;

	private final String name;
	private final String email;
	private final String password;

	public final TypeUser type;

	private User(UUID id, String name, String email, String password, TypeUser type) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.type = type;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public TypeUser getType() {
		return type;
	}

}
