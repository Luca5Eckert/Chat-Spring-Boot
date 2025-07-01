package com.projetospring.chatonline.dtos;


import jakarta.validation.constraints.Max;

public record SendMessageDto(@Max(value=150, message="Message content cannot be longer than 150 characters") String content, RoomIdDto sendFor) {

}
