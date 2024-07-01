package br.com.fiap.backend.dto;

import jakarta.validation.constraints.NotBlank;

public record InsertTrainingData(

        @NotBlank
        String title

) {
}
