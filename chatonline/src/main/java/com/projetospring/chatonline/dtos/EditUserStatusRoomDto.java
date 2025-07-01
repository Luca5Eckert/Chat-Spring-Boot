package com.projetospring.chatonline.dtos;

import com.projetospring.chatonline.model.TypeRoomAccess;
import com.projetospring.chatonline.model.UserStatusRoomId;
import jakarta.validation.constraints.NotBlank;

public record EditUserStatusRoomDto(@NotBlank UserStatusRoomId id,@NotBlank TypeRoomAccess typeRoomAccess) {
}
