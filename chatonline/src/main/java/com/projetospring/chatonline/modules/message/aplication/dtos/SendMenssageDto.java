package com.projetospring.chatonline.modules.message.aplication.dtos;

import com.projetospring.chatonline.modules.room.domain.Room;

public record SendMenssageDto(String content, Room sendFor) {

}
