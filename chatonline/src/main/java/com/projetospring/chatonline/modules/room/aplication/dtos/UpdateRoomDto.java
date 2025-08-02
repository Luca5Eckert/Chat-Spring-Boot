package com.projetospring.chatonline.modules.room.aplication.dtos;

import jakarta.validation.constraints.NotBlank;

public record UpdateRoomDto( @NotBlank String nameRoom, @NotBlank String description) {
    
}
