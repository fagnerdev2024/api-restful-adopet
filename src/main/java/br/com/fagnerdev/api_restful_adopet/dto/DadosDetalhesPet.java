package br.com.fagnerdev.api_restful_adopet.dto;

import br.com.fagnerdev.api_restful_adopet.model.Pet;
import br.com.fagnerdev.api_restful_adopet.model.TipoPet;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosDetalhesPet(

        @NotNull
        Long id,

        @NotNull
        TipoPet tipo,

        @NotBlank
        String nome,

        @NotBlank
        String raca,

        @Min(0)
        Integer idade
) {


    public DadosDetalhesPet(Pet pet) {
        this(pet.getId(), pet.getTipo(), pet.getNome(), pet.getRaca(), pet.getIdade());
    }
}