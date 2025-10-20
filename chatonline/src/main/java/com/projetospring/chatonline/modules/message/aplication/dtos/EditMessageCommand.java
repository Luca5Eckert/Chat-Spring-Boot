package com.projetospring.chatonline.modules.message.aplication.dtos;

import com.projetospring.chatonline.modules.user.domain.UserEntity;

public record EditMessageCommand(EditMessageDto editMessageDto, UserEntity userEntity) {
}
