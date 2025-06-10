package com.projetospring.chatonline.dtos;

import com.projetospring.chatonline.model.User;

public record MessageDto(String content, User sendBy) {

}
