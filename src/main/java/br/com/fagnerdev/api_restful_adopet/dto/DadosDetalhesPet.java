package br.com.fagnerdev.api_restful_adopet.dto;

import br.com.fagnerdev.api_restful_adopet.model.Pet;
import br.com.fagnerdev.api_restful_adopet.model.TipoPet;

public record DadosDetalhesPet(Long id, TipoPet tipo, String nome, String raca, Integer idade) {

    public DadosDetalhesPet(Pet pet) {
        this(pet.getId(), pet.getTipo(), pet.getNome(), pet.getRaca(), pet.getIdade());
    }
}
