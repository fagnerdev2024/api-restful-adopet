package br.com.fagnerdev.api_restful_adopet.validacoes;

import br.com.fagnerdev.api_restful_adopet.dto.SolicitacaoAdocaoDto;
import br.com.fagnerdev.api_restful_adopet.exception.ValidacaoException;
import br.com.fagnerdev.api_restful_adopet.model.Adocao;
import br.com.fagnerdev.api_restful_adopet.model.Pet;
import br.com.fagnerdev.api_restful_adopet.model.StatusAdocao;
import br.com.fagnerdev.api_restful_adopet.repository.AdocaoRepository;
import br.com.fagnerdev.api_restful_adopet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidacaoPetComAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao {

    @Autowired
    private AdocaoRepository adocaoRepository;

    @Autowired
    private PetRepository petRepository;

    public void validar(SolicitacaoAdocaoDto dto) {
        List<Adocao> adocoes = adocaoRepository.findAll();
        Pet pet = petRepository.getReferenceById(dto.idPet());
        for (Adocao a : adocoes) {
            if (a.getPet() == pet && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO) {
                throw new ValidacaoException("Pet já está aguardando avaliação para ser adotado!");

            }
        }
    }

}
