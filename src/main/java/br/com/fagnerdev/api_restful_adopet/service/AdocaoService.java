package br.com.fagnerdev.api_restful_adopet.service;


import br.com.fagnerdev.api_restful_adopet.dto.AprovacaoAdocaoDto;
import br.com.fagnerdev.api_restful_adopet.dto.ReprovacaoAdocaoDto;
import br.com.fagnerdev.api_restful_adopet.dto.SolicitacaoAdocaoDto;
import br.com.fagnerdev.api_restful_adopet.exception.ValidacaoException;
import br.com.fagnerdev.api_restful_adopet.model.Adocao;
import br.com.fagnerdev.api_restful_adopet.model.Pet;
import br.com.fagnerdev.api_restful_adopet.model.StatusAdocao;
import br.com.fagnerdev.api_restful_adopet.model.Tutor;
import br.com.fagnerdev.api_restful_adopet.repository.AdocaoRepository;
import br.com.fagnerdev.api_restful_adopet.repository.PetRepository;
import br.com.fagnerdev.api_restful_adopet.repository.TutorRepository;
import br.com.fagnerdev.api_restful_adopet.validacoes.ValidacaoSolicitacaoAdocao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AdocaoService {

    @Autowired
    private AdocaoRepository adocaoRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private List<ValidacaoSolicitacaoAdocao> validacaoSolicitacaoAdocaos;



    public void solicitar(SolicitacaoAdocaoDto solicitacaoAdocaoDto) {
        Pet pet = petRepository.getReferenceById(solicitacaoAdocaoDto.idPet());
        Tutor tutor = tutorRepository.getReferenceById(solicitacaoAdocaoDto.idTutor());

        validacaoSolicitacaoAdocaos.forEach(v -> v.validar(solicitacaoAdocaoDto));

        Adocao adocao = new Adocao();
        adocao.setData(LocalDateTime.now());
        adocao.setStatus(StatusAdocao.AGUARDANDO_AVALIACAO);
        adocao.setPet(pet);
        adocao.setTutor(tutor);
        adocao.setMotivo(solicitacaoAdocaoDto.motivo());

        emailService.enviarEmail(
                adocao.getPet().getAbrigo().getEmail(),
                "Solicitação de adoção",
                "Olá " +adocao.getPet().getAbrigo().getNome() +"!\n\nUma solicitação de adoção foi registrada hoje para o pet: " +adocao.getPet().getNome() +". \nFavor avaliar para aprovação ou reprovação.");
    }

    public void aprovar(AprovacaoAdocaoDto aprovacaoAdocaoDto) {
        Adocao adocao = adocaoRepository.getReferenceById(aprovacaoAdocaoDto.idAdocao());
        adocao.setStatus(StatusAdocao.APROVADO);
        adocao.setStatus(StatusAdocao.APROVADO);
        adocaoRepository.save(adocao);

        emailService.enviarEmail(
                adocao.getPet().getAbrigo().getEmail(),
                "Adoção aprovada",
                "Parabéns " +adocao.getTutor().getNome() +"!\n\nSua adoção do pet " +adocao.getPet().getNome() +", solicitada em " +adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +", foi aprovada.\nFavor entrar em contato com o abrigo " +adocao.getPet().getAbrigo().getNome() +" para agendar a busca do seu pet.");
    }

    public void reprovar(ReprovacaoAdocaoDto reprovacaoAdocaoDto) {
        Adocao adocao = adocaoRepository.getReferenceById(reprovacaoAdocaoDto.idAdocao());
        adocao.setStatus (StatusAdocao.REPROVADO);
        adocao.setJustificativaStatus (reprovacaoAdocaoDto.justificativa());
        adocao.setStatus(StatusAdocao.REPROVADO);
        adocaoRepository.save(adocao);

        emailService.enviarEmail(
                adocao.getPet().getAbrigo().getEmail(),
                "Solicitação de adoção",
                "Olá " +adocao.getTutor().getNome() +"!\n\nInfelizmente sua adoção do pet " +adocao.getPet().getNome() +", solicitada em " +adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +", foi reprovada pelo abrigo " +adocao.getPet().getAbrigo().getNome() +" com a seguinte justificativa: " +adocao.getJustificativaStatus());
    }

}
