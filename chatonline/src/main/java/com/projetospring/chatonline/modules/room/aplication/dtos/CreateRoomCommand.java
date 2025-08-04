package com.projetospring.chatonline.modules.room.aplication.dtos;

import com.projetospring.chatonline.modules.room.domain.enums.TypeRoom;
import com.projetospring.chatonline.modules.user.domain.UserEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record CreateRoomCommand(CreateRoomDto createRoomDto, UserEntity user) {
}
