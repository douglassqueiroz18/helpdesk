package douglas.com.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import douglas.com.helpdesk.models.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
    // Custom query methods can be defined here if needed
    // For example, to find a call by its status:
    // List<Chamado> findByStatus(String status);
    
}
