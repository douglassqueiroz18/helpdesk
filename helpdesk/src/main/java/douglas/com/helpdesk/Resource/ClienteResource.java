package douglas.com.helpdesk.Resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import douglas.com.helpdesk.dtos.ClienteDTO;
import douglas.com.helpdesk.models.Cliente;
import douglas.com.helpdesk.services.ClienteService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping(value = "/Clientes")
public class ClienteResource {

    @Autowired
    private ClienteService ClienteService;
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
        Cliente obj = ClienteService.findById(id);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> list = ClienteService.findAll();
        List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).toList();
        return ResponseEntity.ok().body(listDto);
    }
    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDto) {
        System.out.println("Recebendo os dados do técnico no ClienteResource: " + objDto);
        if (objDto.getCpf() == null || objDto.getCpf().isEmpty()) {
            System.out.println("CPF não pode ser nulo ou vazio.");
            return ResponseEntity.badRequest().build(); // Retorna um erro 400 Bad Request se o CPF for inválido
        }
        if (objDto.getEmail() == null || objDto.getEmail().isEmpty()) {
            System.out.println("Email não pode ser nulo ou vazio.");
            return ResponseEntity.badRequest().build(); // Retorna um erro 400 Bad Request se o email for inválido
        }
        if (objDto.getNome() == null || objDto.getNome().isEmpty()) {
            System.out.println("Nome não pode ser nulo ou vazio.");
            return ResponseEntity.badRequest().build(); // Retorna um erro 400 Bad Request se o nome for inválido
        }
        Cliente newObj = ClienteService.createCliente(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClienteDTO(newObj));
    }
    @PutMapping(value="/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO objDto) {
        Cliente obj = ClienteService.updateById(id, objDto);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id) {
        ClienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}