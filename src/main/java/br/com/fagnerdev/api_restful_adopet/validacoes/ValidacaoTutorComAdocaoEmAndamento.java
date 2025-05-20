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
public class ValidacaoTutorComAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao {

    @Autowired
    private AdocaoRepository adocaoRepository;

    @Autowired
    private TutorRepository tutorRepository;

    public void validar(SolicitacaoAdocaoDto dto) {
        List<Adocao> adocoes = adocaoRepository.findAll();
        Tutor tutor = tutorRepository.getReferenceById(dto.idTutor());
        for (Adocao a : adocoes) {
            if (a.getTutor() == tutor && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO) {
                throw new ValidacaoException("Tutor já possui outra adoção aguardando avaliação!");
            }
        }
    }

}