package com.projetospring.chatonline.dtos;

import com.projetospring.chatonline.model.Room;

public record SendMenssageDto(String content, Room sendFor) {

}
