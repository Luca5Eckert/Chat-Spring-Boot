package com.projetospring.chatonline.dtos;

import java.util.UUID;

import com.projetospring.chatonline.model.Room;
import com.projetospring.chatonline.model.TypeRoom;

import jakarta.validation.constraints.NotBlank;

public record RoomDto(UUID id, @NotBlank String name, @NotBlank TypeRoom type, @NotBlank String description,
		@NotBlank int numberOfPeople) {
	public RoomDto(Room room) {
		this(room.getId(), room.getName(), room.getType(), room.getDescription(), room.getNumberOfPeople());
	}
}
