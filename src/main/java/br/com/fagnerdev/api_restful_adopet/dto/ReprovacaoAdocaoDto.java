package br.com.fagnerdev.api_restful_adopet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReprovacaoAdocaoDto(

        @NotNull
        Long idAdocao,

        @NotBlank
        String justificativa
) {
}
