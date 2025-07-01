package com.projetospring.chatonline.dtos;

import java.util.UUID;

import com.projetospring.chatonline.model.Room;
import com.projetospring.chatonline.model.TypeRoom;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record RoomDto(@NotBlank UUID id, @NotBlank @Max(value=20, message="Room name cannot be longer than 20 characters") String name, @NotBlank TypeRoom type, @NotBlank @Max(value=150, message="Room description cannot be longer than 150 characters") String description,
					  @NotBlank int numberOfPeople) {
	public RoomDto(Room room) {
		this(room.getId(), room.getName(), room.getType(), room.getDescription(), room.getNumberOfPeople());
	}
}
