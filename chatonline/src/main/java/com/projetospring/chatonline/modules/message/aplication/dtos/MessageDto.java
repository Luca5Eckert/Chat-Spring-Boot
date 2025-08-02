package com.projetospring.chatonline.modules.message.aplication.dtos;

import com.projetospring.chatonline.modules.room.domain.Room;
import com.projetospring.chatonline.modules.user.domain.User;

public record MessageDto(String content, User sendBy, Room sendFor) {

}
