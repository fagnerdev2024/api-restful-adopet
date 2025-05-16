package br.com.fagnerdev.api_restful_adopet.dto;

import jakarta.validation.constraints.NotNull;

public record AprovacaoAdocaoDto(

        @NotNull
        Long idAdocao
) {
}
