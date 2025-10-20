package com.projetospring.chatonline.modules.room.aplication.dtos;

import com.projetospring.chatonline.modules.user.domain.UserEntity;

public record UpdateRoomCommand(UpdateRoomDto updateRoomDto, UserEntity userEntity) {
}
