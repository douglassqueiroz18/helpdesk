package douglas.com.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import douglas.com.helpdesk.models.Chamado;
import douglas.com.helpdesk.repositories.ChamadoRepository;
import douglas.com.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    public Chamado findById(Integer id) {
        Optional<Chamado> obj = chamadoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Chamado.class.getName()));
    }
    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }
}
