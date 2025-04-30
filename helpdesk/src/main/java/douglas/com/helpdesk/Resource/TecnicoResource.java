package douglas.com.helpdesk.Resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import douglas.com.helpdesk.dtos.TecnicoDTO;
import douglas.com.helpdesk.models.Tecnico;
import douglas.com.helpdesk.services.TecnicoService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService tecnicoService;
    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
        Tecnico obj = tecnicoService.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }
    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll() {
        List<Tecnico> list = tecnicoService.findAll();
        List<TecnicoDTO> listDto = list.stream().map(obj -> new TecnicoDTO(obj)).toList();
        return ResponseEntity.ok().body(listDto);
    }
    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDto) {
        System.out.println("Recebendo os dados do técnico no tecnicoResource: " + objDto);
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
        Tecnico newObj = tecnicoService.createTecnico(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).body(new TecnicoDTO(newObj));
    }
    @PutMapping(value="/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO objDto) {
        Tecnico obj = tecnicoService.updateById(id, objDto);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }
}
