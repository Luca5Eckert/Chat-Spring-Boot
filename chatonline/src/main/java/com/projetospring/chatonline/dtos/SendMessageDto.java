package com.projetospring.chatonline.dtos;


public record SendMessageDto(String content, RoomIdDto sendFor) {

}
