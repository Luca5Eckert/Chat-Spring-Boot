package com.projetospring.chatonline.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.projetospring.chatonline.model.TypeRoom;

import jakarta.validation.constraints.NotBlank;

public record RoomDto(UUID id, @NotBlank String nameRoom, @NotBlank TypeRoom type, @NotBlank String description, @NotBlank LocalDateTime createAt, @NotBlank int numberOfPeople){

}
