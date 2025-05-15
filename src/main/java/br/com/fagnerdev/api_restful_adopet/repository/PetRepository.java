package br.com.fagnerdev.api_restful_adopet.repository;

import br.com.fagnerdev.api_restful_adopet.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {

}
