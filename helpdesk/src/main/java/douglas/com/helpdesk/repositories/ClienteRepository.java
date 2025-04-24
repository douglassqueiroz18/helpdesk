package douglas.com.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import douglas.com.helpdesk.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    // Custom query methods can be defined here if needed
    // For example, to find a client by their email:
    // Optional<Cliente> findByEmail(String email);
    
}
