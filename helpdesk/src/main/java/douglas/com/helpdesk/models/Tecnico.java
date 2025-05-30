package douglas.com.helpdesk.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import douglas.com.helpdesk.dtos.TecnicoDTO;
import douglas.com.helpdesk.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Tecnico extends Pessoa{
    private static final long serialVersionUID = 1L; // Número de versão para controle na serialização.
    @JsonIgnore
    @OneToMany(mappedBy = "tecnico") // Indica que um técnico pode ter vários chamados associados a ele.
    // "mappedBy" indica que a relação é bidirecional e que o campo "tecnico" na classe Chamado é o responsável pela relação.
    private List<Chamado> chamados = new ArrayList<>(); // Lista de chamados associados ao cliente

    public Tecnico(){
        super(); // Chama o construtor da classe pai (Pessoa)
        addPerfil(Perfil.TECNICO); // Por padrão, toda pessoa começa com o perfil "Cliente".
    }
    public Tecnico(Integer id, String nome, String cpf, String email, String senha, List<Perfil> perfis){
        super(id, nome, cpf, email, senha); // Chama o construtor da classe pai (Pessoa) com os parâmetros fornecidos
        addPerfil(Perfil.TECNICO); // Por padrão, toda pessoa começa com o perfil "Cliente".
    }
    public Tecnico(TecnicoDTO obj){
        super(); // Chama o construtor da classe pai (Pessoa) com o objeto fornecido
        this.id = obj.getId(); // Define o ID do técnico com o ID do objeto fornecido
        this.nome = obj.getNome(); // Define o nome do técnico com o nome do objeto fornecido
        this.cpf = obj.getCpf(); // Define o CPF do técnico com o CPF do objeto fornecido
        this.email = obj.getEmail(); // Define o email do técnico com o email do objeto fornecido
        this.senha = obj.getSenha(); // Define a senha do técnico com a senha do objeto fornecido
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet()); // Define os perfis do técnico com os perfis do objeto fornecido
        this.dataCriacao = obj.getDataCriacao(); // Define a data de criação do técnico com a data de criação do objeto fornecido
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
