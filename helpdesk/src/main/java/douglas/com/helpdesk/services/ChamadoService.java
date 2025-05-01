package douglas.com.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import douglas.com.helpdesk.dtos.ChamadoDTO;
import douglas.com.helpdesk.enums.Prioridade;
import douglas.com.helpdesk.enums.Status;
import douglas.com.helpdesk.models.Chamado;
import douglas.com.helpdesk.models.Cliente;
import douglas.com.helpdesk.models.Tecnico;
import douglas.com.helpdesk.repositories.ChamadoRepository;
import douglas.com.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private ClienteService clienteService; // Adicionando o repositório de Cliente para validação de CPF e email
    @Autowired
    private TecnicoService tecnicoService; // Adicionando o repositório de Tecnico para validação de CPF e email

    public Chamado findById(Integer id) {
        Optional<Chamado> obj = chamadoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Chamado.class.getName()));
    }

    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }

    public Chamado createChamado(ChamadoDTO objDto) {
        return chamadoRepository.save(newChamado(objDto)); // Cria um novo objeto Chamado a partir do DTO
    }

    private Chamado newChamado(ChamadoDTO obj) {
        Tecnico tecnico = tecnicoService.findById(obj.getTecnico()); // Cria um novo objeto Chamado a partir do DTO
        Cliente cliente = clienteService.findById(obj.getCliente()); // Cria um novo objeto Chamado a partir do DTO

        Chamado chamado = new Chamado(); // Cria um novo objeto Chamado a partir do DTO
        if (obj.getId() != null) {
            chamado.setId(obj.getId()); // Garante que o ID do novo objeto seja nulo, pois ele será gerado
                                        // automaticamente pelo banco de dados
        }
        chamado.setTecnico(tecnico); // Garante que o ID do novo objeto seja nulo, pois ele será gerado
        // automaticamente pelo banco de dados
        chamado.setCliente(cliente); // Garante que o ID do novo objeto seja nulo, pois ele será gerado
        // automaticamente pelo banco de dados
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade())); // Garante que o ID do novo objeto seja nulo,
        // pois ele será gerado automaticamente pelo
        // banco de dados
        chamado.setStatus(Status.toEnum(obj.getStatus())); // Garante que o ID do novo objeto seja nulo, pois ele será
        // gerado automaticamente pelo banco de dados
        chamado.setTitulo(obj.getTitulo()); // Garante que o ID do novo objeto seja nulo, pois ele será gerado
        // automaticamente pelo banco de dados
        chamado.setObservacoes(obj.getObservacoes()); // Garante que o ID do novo objeto seja nulo, pois ele será gerado
                                                      // automaticamente pelo banco de dados
        return chamado; // Salva o novo objeto no banco de dados
    }
}
