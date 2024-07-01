package br.com.fiap.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserData(
        Long id,
        String name,
        String email,
        String password
) {
}
