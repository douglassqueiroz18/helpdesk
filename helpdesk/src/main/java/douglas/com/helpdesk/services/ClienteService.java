package douglas.com.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import douglas.com.helpdesk.dtos.ClienteDTO;
import douglas.com.helpdesk.models.Pessoa;
import douglas.com.helpdesk.models.Cliente;
import douglas.com.helpdesk.repositories.PessoaRepository;
import douglas.com.helpdesk.repositories.ClienteRepository;
import douglas.com.helpdesk.services.exceptions.DataIntegrityViolationException;
import douglas.com.helpdesk.services.exceptions.ObjectnotFoundException;
import jakarta.validation.Valid;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository ClienteRepository;
    @Autowired
    private PessoaRepository pessoaRepository; // Adicionando o repositório de Pessoa para validação de CPF e email
    @Autowired
    private BCryptPasswordEncoder encoder;
    public Cliente findById(Integer id) {
        Optional<Cliente> obj = ClienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }
    public List<Cliente> findAll() {
        return ClienteRepository.findAll();
    }
    public Cliente createCliente(ClienteDTO objDto) {
        objDto.setId(null); // Garante que o ID do novo objeto seja nulo, pois ele será gerado automaticamente pelo banco de dados
        objDto.setSenha(encoder.encode(objDto.getSenha())); // Criptografa a senha antes de salvar
        validaPorCpfEEmail(objDto); // Valida se o CPF e o email já existem no banco de dados
        Cliente newObj = new Cliente(objDto); // Cria um novo objeto Cliente a partir do DTO
        return ClienteRepository.save(newObj); // Salva o novo objeto no banco de dados
    }
    public Cliente updateById(Integer id, @Valid ClienteDTO objDto) {
        objDto.setId(id); // Garante que o ID do novo objeto seja nulo, pois ele será gerado automaticamente pelo banco de dados
        Cliente oldObj = findById(id); // Busca o objeto existente no banco de dados
        validaPorCpfEEmail(objDto); // Valida se o CPF e o email já existem no banco de dados
        oldObj = new Cliente(objDto); // Cria um novo objeto Cliente a partir do DTO
        return ClienteRepository.save(oldObj); // Salva o objeto atualizado no banco de dados
    }
    public void deleteById(Integer id) {
        Cliente obj = findById(id); // Busca o objeto existente no banco de dados
        if (obj.getChamados().size() > 0) {
            // Se o técnico tiver chamados associados, lança uma exceção
            throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado! Id: " + id);
        }else {
            ClienteRepository.deleteById(id); // Deleta o objeto do banco de dados
        }
    }
    private void validaPorCpfEEmail(ClienteDTO objDto) {
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
