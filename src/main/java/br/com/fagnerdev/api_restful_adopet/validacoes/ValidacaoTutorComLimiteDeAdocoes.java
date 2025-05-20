package br.com.fagnerdev.api_restful_adopet.validacoes;

import br.com.fagnerdev.api_restful_adopet.dto.SolicitacaoAdocaoDto;
import br.com.fagnerdev.api_restful_adopet.exception.ValidacaoException;
import br.com.fagnerdev.api_restful_adopet.model.Adocao;
import br.com.fagnerdev.api_restful_adopet.model.StatusAdocao;
import br.com.fagnerdev.api_restful_adopet.model.Tutor;
import br.com.fagnerdev.api_restful_adopet.repository.AdocaoRepository;
import br.com.fagnerdev.api_restful_adopet.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidacaoTutorComLimiteDeAdocoes implements ValidacaoSolicitacaoAdocao {

    @Autowired
    private AdocaoRepository adocaoRepository;

    @Autowired
    private TutorRepository tutorRepository;

    public void validar(SolicitacaoAdocaoDto dto) {
        List<Adocao> adocoes = adocaoRepository.findAll();
        Tutor tutor = tutorRepository.getReferenceById(dto.idTutor());
        for (Adocao a : adocoes) {
            int contador = 0;
            if (a.getTutor() == tutor && a.getStatus() == StatusAdocao.APROVADO) {
                contador = contador + 1;
            }
            if (contador == 5) {
                throw new ValidacaoException("Tutor chegou ao limite máximo de 5 adoções!");
            }
        }
    }

}
