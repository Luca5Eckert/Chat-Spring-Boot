package com.projetospring.chatonline.dtos;

import com.projetospring.chatonline.model.Room;
import com.projetospring.chatonline.model.User;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record MessageDto(@NotBlank @Max(value=150, message="Message content cannot be longer than 150 characters") String content, @NotBlank User sendBy, @NotBlank Room sendFor) {

}
