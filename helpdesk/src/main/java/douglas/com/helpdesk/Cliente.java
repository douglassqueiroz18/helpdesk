package douglas.com.helpdesk;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa{
    private List<Chamado> chamados = new ArrayList<>(); // Lista de chamados associados ao cliente
    
    public Cliente(){
        super(); // Chama o construtor da classe pai (Pessoa)
    }
    public Cliente(Integer id, String nome, String cpf, String email, String senha){
        super(id, nome, cpf, email, senha); // Chama o construtor da classe pai (Pessoa) com os parâmetros fornecidos
    }

    public List<Chamado> getChamados() {
        return chamados; // Retorna a lista de chamados associados ao cliente
    }
    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados; // Define a lista de chamados associados ao cliente
    }
}
