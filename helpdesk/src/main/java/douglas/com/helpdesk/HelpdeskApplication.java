package douglas.com.helpdesk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import douglas.com.helpdesk.enums.Perfil;
import douglas.com.helpdesk.enums.Status;
import douglas.com.helpdesk.enums.Prioridade;
import douglas.com.helpdesk.models.Chamado;
import douglas.com.helpdesk.models.Cliente;
import douglas.com.helpdesk.models.Tecnico;
import douglas.com.helpdesk.repositories.ChamadoRepository;
import douglas.com.helpdesk.repositories.ClienteRepository;
import douglas.com.helpdesk.repositories.TecnicoRepository;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Tecnico tec1 = new Tecnico(null, "Douglas", "12345678900", "douglas@email.com", "123456");
		tec1.addPerfil(Perfil.ADMIN);

		Cliente cli1 = new Cliente(null, "marcos", "12345678901", "marcos@email.com", "123");
		cli1.addPerfil(Perfil.CLIENTE);

		Chamado chamado1 = new Chamado(null, Status.ABERTO, Prioridade.ALTA, "Chamado 1", "Primeiro chamado", tec1, cli1);
	
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(chamado1));
		// Chamado chamado2 = new Chamado(null, Status.ABERTO, Prioridade.ALTA, "Chamado 2", "Primeiro chamado", tec1, cli1);
	}

}
