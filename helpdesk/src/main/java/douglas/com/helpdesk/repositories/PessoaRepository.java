package douglas.com.helpdesk.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import douglas.com.helpdesk.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    // Custom query methods can be defined here if needed
    // For example, to find a person by their email:
    // Optional<Pessoa> findByEmail(String email);
    Optional<Pessoa> findByCpf(String cpf);
    Optional<Pessoa> findByEmail(String email);
}
