package br.com.fagnerdev.api_restful_adopet.repository;

import br.com.fagnerdev.api_restful_adopet.model.Adocao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdocaoRepository extends JpaRepository<Adocao, Long> {

}
