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

@Entity
@Table(name = "room_tb")
public class Room {
	private final static int MAX_PEOPLE = 10;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TypeRoom type;

	@Column(nullable = false)
	private int numberOfPeople;

	@Column(nullable = false)
	private LocalDateTime createAt;

	@Column(nullable = false)
	private String description;

	private Room(UUID id, TypeRoom type, int numberOfPeople, LocalDateTime createAt, String description) {
		this.id = id;
		this.type = type;
		this.numberOfPeople = numberOfPeople;
		this.createAt = createAt;
		this.description = description;
	}

	public static Room createRoom(UUID id, TypeRoom type, int numberOfPeople, LocalDateTime createAt,
			String description) {
		return new Room(id, type, numberOfPeople, createAt, description);
	}

	public String getDescription() {
		return new String(description);
	}

	public boolean isItFull() {
		return numberOfPeople == MAX_PEOPLE;
	}

	public boolean canAdd(int numberOfPeopleAdd) {
		return (numberOfPeopleAdd + this.numberOfPeople) <= MAX_PEOPLE;
	}

	public static int getMaxPeople() {
		return MAX_PEOPLE;
	}

	public UUID getId() {
		return id;
	}

	public TypeRoom getType() {
		return type;
	}

	public int getNumberOfPeople() {
		return numberOfPeople;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

}
