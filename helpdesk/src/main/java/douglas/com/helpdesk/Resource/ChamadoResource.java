package douglas.com.helpdesk.Resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
