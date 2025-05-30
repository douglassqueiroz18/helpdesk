package douglas.com.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import douglas.com.helpdesk.models.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
    // Custom query methods can be defined here if needed
    // For example, to find a technician by their email:
    // Optional<Tecnico> findByEmail(String email);
    
}
