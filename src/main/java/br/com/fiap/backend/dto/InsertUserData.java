package br.com.fiap.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record InsertUserData(

        @NotBlank
        String login,

        @NotBlank
        String password

) {
}
