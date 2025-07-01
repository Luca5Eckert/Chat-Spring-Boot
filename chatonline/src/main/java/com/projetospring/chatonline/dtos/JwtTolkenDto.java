package com.projetospring.chatonline.dtos;

import jakarta.validation.constraints.NotBlank;

public record JwtTolkenDto(@NotBlank String tolken) {
}
