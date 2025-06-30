package com.projetospring.chatonline.dtos;

import jakarta.validation.Valid;

public record AddUserRoomDto(@Valid RoomIdDto roomIdDto,@Valid UserIdDto userIdDto) {
}
