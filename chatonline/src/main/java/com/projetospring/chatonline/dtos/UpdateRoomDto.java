package com.projetospring.chatonline.dtos;

import jakarta.validation.constraints.NotBlank;

public record UpdateRoomDto( @NotBlank String nameRoom, @NotBlank String description) {
    
}
