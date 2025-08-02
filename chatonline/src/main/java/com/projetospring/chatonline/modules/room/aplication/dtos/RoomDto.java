package com.projetospring.chatonline.modules.room.aplication.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.projetospring.chatonline.modules.room.domain.enums.TypeRoom;

import jakarta.validation.constraints.NotBlank;

public record RoomDto(UUID id, @NotBlank String nameRoom, @NotBlank TypeRoom type, @NotBlank String description, @NotBlank LocalDateTime createAt, @NotBlank int numberOfPeople){

}
