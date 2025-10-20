package com.projetospring.chatonline.modules.message.aplication.dtos;

import com.projetospring.chatonline.modules.user.domain.UserEntity;

import java.util.UUID;

public record DeleteMessageCommand(UUID messageId, UserEntity userEntity) {
}
