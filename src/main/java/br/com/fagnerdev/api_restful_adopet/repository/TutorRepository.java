package br.com.fagnerdev.api_restful_adopet.repository;

import br.com.fagnerdev.api_restful_adopet.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

    boolean existsByTelefone(String telefone);

    boolean existsByEmail(String email);

}
