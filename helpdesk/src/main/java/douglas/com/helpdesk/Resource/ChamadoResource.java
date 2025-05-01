package douglas.com.helpdesk.Resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import douglas.com.helpdesk.dtos.ChamadoDTO;
import douglas.com.helpdesk.models.Chamado;
import douglas.com.helpdesk.services.ChamadoService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping(value = "/Chamados")
public class ChamadoResource {
    
    @Autowired
    private ChamadoService chamadoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
        Chamado obj = chamadoService.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(obj));
    }
    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll() {
        List<Chamado> list = chamadoService.findAll();
        List<ChamadoDTO> listDto = list.stream().map(obj -> new ChamadoDTO(obj)).toList();
        return ResponseEntity.ok().body(listDto);
    }
    @PostMapping
    public ResponseEntity<ChamadoDTO> create(@RequestBody ChamadoDTO objDto) {
        Chamado obj = chamadoService.createChamado(objDto); // Cria um novo objeto Chamado a partir do DTO
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> update(@PathVariable Integer id, @RequestBody ChamadoDTO objDto) {
        Chamado obj = chamadoService.updateChamado(id, objDto); // Cria um novo objeto Chamado a partir do DTO
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
