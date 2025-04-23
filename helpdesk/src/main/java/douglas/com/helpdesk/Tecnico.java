package douglas.com.helpdesk;

import java.util.ArrayList;
import java.util.List;

public class Tecnico extends Pessoa{
    private List<Chamado> chamados = new ArrayList<>(); // Lista de chamados associados ao cliente

    public Tecnico(){
        super(); // Chama o construtor da classe pai (Pessoa)
    }
    public Tecnico(Integer id, String nome, String cpf, String email, String senha){
        super(id, nome, cpf, email, senha); // Chama o construtor da classe pai (Pessoa) com os parâmetros fornecidos
    }
}
