package br.com.fagnerdev.api_restful_adopet.controller;

import br.com.fagnerdev.api_restful_adopet.dto.AprovacaoAdocaoDto;
import br.com.fagnerdev.api_restful_adopet.dto.ReprovacaoAdocaoDto;
import br.com.fagnerdev.api_restful_adopet.dto.SolicitacaoAdocaoDto;
import br.com.fagnerdev.api_restful_adopet.model.Adocao;
import br.com.fagnerdev.api_restful_adopet.service.AdocaoService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adocoes")
public class AdocaoController {

    @Autowired
    private AdocaoService adocaoService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> solicitar(@RequestBody @Valid SolicitacaoAdocaoDto solicitacaoAdocaoDto) {
        try {
            this.adocaoService.solicitar(solicitacaoAdocaoDto);
            return ResponseEntity.ok("Adoção solciitada com sucesso!");
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/aprovar")
    @Transactional
    public ResponseEntity<String> aprovar(@RequestBody @Valid AprovacaoAdocaoDto aprovacaoAdocaoDto) {
        this.adocaoService.aprovar(aprovacaoAdocaoDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reprovar")
    @Transactional
    public ResponseEntity<String> reprovar(@RequestBody @Valid ReprovacaoAdocaoDto reprovacaoAdocaoDto) {
        this.adocaoService.reprovar(reprovacaoAdocaoDto);
        return ResponseEntity.ok().build();
    }

}

