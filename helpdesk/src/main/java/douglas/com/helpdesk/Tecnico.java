package douglas.com.helpdesk;

import java.util.ArrayList;
import java.util.List;

import douglas.com.helpdesk.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Tecnico extends Pessoa{
    private static final long serialVersionUID = 1L; // Número de versão para controle na serialização.

    @OneToMany(mappedBy = "tecnico") // Indica que um técnico pode ter vários chamados associados a ele.
    // "mappedBy" indica que a relação é bidirecional e que o campo "tecnico" na classe Chamado é o responsável pela relação.
    private List<Chamado> chamados = new ArrayList<>(); // Lista de chamados associados ao cliente

    public Tecnico(){
        super(); // Chama o construtor da classe pai (Pessoa)
        addPerfil(Perfil.TECNICO); // Por padrão, toda pessoa começa com o perfil "Cliente".
    }
    public Tecnico(Integer id, String nome, String cpf, String email, String senha){
        super(id, nome, cpf, email, senha); // Chama o construtor da classe pai (Pessoa) com os parâmetros fornecidos
    }
    public List<Chamado> getChamados() {
        return chamados; // Retorna a lista de chamados associados ao cliente
    }
    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados; // Define a lista de chamados associados ao cliente
    }
}
