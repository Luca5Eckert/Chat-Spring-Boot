package com.projetospring.chatonline.modules.message.aplication.dtos;

import com.projetospring.chatonline.modules.user.domain.UserEntity;

public record SendMessageCommand(SendMenssageDto sendMenssageDto, UserEntity userEntity) {
}
