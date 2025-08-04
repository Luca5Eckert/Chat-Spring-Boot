package com.projetospring.chatonline.modules.message.aplication.dtos;

import com.projetospring.chatonline.modules.room.domain.Room;

import java.util.UUID;

public record SendMenssageDto(String content, UUID roomId) {

}
