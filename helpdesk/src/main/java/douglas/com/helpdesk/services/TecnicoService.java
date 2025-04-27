package douglas.com.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import douglas.com.helpdesk.models.Tecnico;
import douglas.com.helpdesk.repositories.TecnicoRepository;
import douglas.com.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class TecnicoService {
    
    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Tecnico.class.getName()));
    }
    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }
}
