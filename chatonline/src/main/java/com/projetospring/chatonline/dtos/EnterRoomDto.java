package com.projetospring.chatonline.dtos;

import jakarta.validation.constraints.NotBlank;

public record EnterRoomDto(@NotBlank RoomIdDto roomIdDto, @NotBlank String password) {

}

