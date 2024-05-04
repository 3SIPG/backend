package br.com.fiap.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserData(
        Long id,

        String login,

        String password
) {
}
