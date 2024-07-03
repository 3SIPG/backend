package br.com.fiap.backend.dto;

import jakarta.validation.constraints.NotBlank;

public record InsertOnboardData(

        @NotBlank
        String title

) {
}
