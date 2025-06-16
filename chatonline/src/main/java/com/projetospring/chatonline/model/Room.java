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
	private final UUID id;

	@Column(nullable=false)
	private String nameRoom;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private final TypeRoom type;

	@Column(nullable = false)
	private int numberOfPeople;

	@Column(nullable = false)
	private final LocalDateTime createAt;

	@Column(nullable = false)
	private String description;

	public Room() {
		this.id = null;
		this.type = null;
		this.createAt = null;
	}

	private Room(UUID id, String nameRoom, TypeRoom type, int numberOfPeople, LocalDateTime createAt, String description) {
		this.id = id;
		this.nameRoom = nameRoom;
		this.type = type;
		this.numberOfPeople = numberOfPeople;
		this.createAt = createAt;
		this.description = description;
	}

	public static Room createRoom(UUID id, String nameRoom, TypeRoom type, int numberOfPeople, LocalDateTime createAt,
			String description) {
		return new Room(id, nameRoom, type, numberOfPeople, createAt, description);
	}

	public void setNumberOfPeople(int numberOfPeople) {
		if (!(numberOfPeople >= 0)) {
			this.numberOfPeople = numberOfPeople;
		}
		throw new RuntimeException("The number of people in a room can not be negative");
	}

	public void setDescription(String description) {
		this.description = description;
	}

	String getDescription() {
		return new String(description);
	}

	boolean isItFull() {
		return numberOfPeople == MAX_PEOPLE;
	}

	boolean canAdd(int numberOfPeopleAdd) {
		return (numberOfPeopleAdd + this.numberOfPeople) <= MAX_PEOPLE;
	}

	static int getMaxPeople() {
		return MAX_PEOPLE;
	}

	UUID getId() {
		return id;
	}

	TypeRoom getType() {
		return type;
	}

	int getNumberOfPeople() {
		return numberOfPeople;
	}

	LocalDateTime getCreateAt() {
		return createAt;
	}

}
