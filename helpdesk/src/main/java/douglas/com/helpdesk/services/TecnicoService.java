package douglas.com.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import douglas.com.helpdesk.dtos.TecnicoDTO;
import douglas.com.helpdesk.models.Pessoa;
import douglas.com.helpdesk.models.Tecnico;
import douglas.com.helpdesk.repositories.PessoaRepository;
import douglas.com.helpdesk.repositories.TecnicoRepository;
import douglas.com.helpdesk.services.exceptions.DataIntegrityViolationException;
import douglas.com.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class TecnicoService {
    
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private PessoaRepository pessoaRepository; // Adicionando o repositório de Pessoa para validação de CPF e email
    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Tecnico.class.getName()));
    }
    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }
    public Tecnico createTecnico(TecnicoDTO objDto) {
        objDto.setId(null); // Garante que o ID do novo objeto seja nulo, pois ele será gerado automaticamente pelo banco de dados
        validaPorCpfEEmail(objDto); // Valida se o CPF e o email já existem no banco de dados
        Tecnico newObj = new Tecnico(objDto); // Cria um novo objeto Tecnico a partir do DTO
        return tecnicoRepository.save(newObj); // Salva o novo objeto no banco de dados
    }
    private void validaPorCpfEEmail(TecnicoDTO objDto) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDto.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDto.getId()) {
            // Se o CPF já estiver cadastrado e o ID for diferente, lança uma exceção
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema! CPF: ");
        }
        obj = pessoaRepository.findByEmail(objDto.getEmail());
        if(obj.isPresent() && obj.get().getId() != objDto.getId()) {
            // Se o email já estiver cadastrado e o ID for diferente, lança uma exceção
            throw new DataIntegrityViolationException("Email já cadastrado no sistema! Email: ");
        }
    }
}
