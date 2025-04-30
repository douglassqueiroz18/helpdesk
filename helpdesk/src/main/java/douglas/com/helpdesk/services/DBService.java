package douglas.com.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import douglas.com.helpdesk.enums.Perfil;
import douglas.com.helpdesk.enums.Prioridade;
import douglas.com.helpdesk.enums.Status;
import douglas.com.helpdesk.models.Chamado;
import douglas.com.helpdesk.models.Cliente;
import douglas.com.helpdesk.models.Tecnico;
import douglas.com.helpdesk.repositories.ChamadoRepository;
import douglas.com.helpdesk.repositories.ClienteRepository;
import douglas.com.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {       
     
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;
    public void instantiateTestDatabase() {
        // CPF válido (formato XXX.XXX.XXX-XX ou XXXXXXXXXXX)
        Tecnico tec1 = new Tecnico(
            null, 
            "Douglas Silva",  // Nome completo
            "000.000.001-60", // CPF válido gerado aleatoriamente
            "douglas.silva@empresa.com", // Email válido
            "$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi" // Senha BCrypt
        );
        tec1.addPerfil(Perfil.ADMIN);
    
        // Segundo técnico para testes
        Tecnico tec2 = new Tecnico(
            null,
            "Ana Oliveira",
            "000.000.000-25",
            "ana.oliveira@empresa.com",
            "$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi"
        );
        tec2.addPerfil(Perfil.TECNICO);
    
        // Cliente com dados válidos
        Cliente cli1 = new Cliente(
            null,
            "Marcos Souza",
            "001.000.000-30",
            "marcos.souza@cliente.com",
            "$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi"
        );
        cli1.addPerfil(Perfil.CLIENTE);
    
        // Chamados de exemplo
        Chamado chamado1 = new Chamado(
            null, 
            Status.ABERTO, 
            Prioridade.ALTA, 
            "Falha no sistema de login", 
            "Usuários não conseguem acessar o sistema com credenciais válidas",
            tec1, 
            cli1
        );
    
        Chamado chamado2 = new Chamado(
            null,
            Status.ANDAMENTO,
            Prioridade.MEDIA,
            "Problema com impressora",
            "Impressora não reconhece cartucho novo",
            tec2,
            cli1
        );
    
        // Salvando todos os dados
        tecnicoRepository.saveAll(Arrays.asList(tec1, tec2));
        clienteRepository.saveAll(Arrays.asList(cli1));
        chamadoRepository.saveAll(Arrays.asList(chamado1, chamado2));
        
        System.out.println("Banco de dados de teste inicializado com sucesso!");
    }
}
