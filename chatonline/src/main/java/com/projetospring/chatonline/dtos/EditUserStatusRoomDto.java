package com.projetospring.chatonline.dtos;

import com.projetospring.chatonline.model.TypeRoomAccess;
import com.projetospring.chatonline.model.UserStatusRoomId;

public record EditUserStatusRoomDto(UserStatusRoomId id, TypeRoomAccess typeRoomAccess) {
}
