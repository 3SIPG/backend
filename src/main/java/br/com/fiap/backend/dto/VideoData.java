package br.com.fiap.backend.dto;

public record VideoData(

        Long id,
        String title,
        String url,
        String banner,
        String description

) {
}
