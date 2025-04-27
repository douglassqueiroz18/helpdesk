package douglas.com.helpdesk.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import douglas.com.helpdesk.enums.Perfil;
import douglas.com.helpdesk.models.Tecnico;

public class TecnicoDTO implements Serializable{
    private static final long serialVersionUID = 1L; // Número de versão para controle na serialização.
    
    protected Integer id; // Número único que identifica cada pessoa no sistema.
    protected String nome; // Armazena o nome da pessoa (ex: "João Silva").

    protected String cpf; // Cadastro de Pessoa Física (documento único brasileiro).

    protected String email;  // E-mail da pessoa (ex: "joao@email.com").
    protected String senha; // Senha criptografada para login no sistema

    protected Set<Integer> perfis = new HashSet<>(); // Conjunto de papéis que a pessoa tem (ex: Cliente, Admin).
    
    @JsonFormat(pattern = "dd/MM/yyyy") // Formato da data de criação (ex: 01/01/2023).
    protected LocalDate dataCriacao = LocalDate.now();
    public TecnicoDTO() {
        super();
    }
    public TecnicoDTO(Tecnico obj) {
        super();  // Chama o construtor da classe pai (Object).
        this.id = obj.getId();
        this.nome = obj.getNome(); // Inicializa o atributo nome.
        this.cpf = obj.getCpf(); // Inicializa o atributo cpf.
        this.email = obj.getEmail(); // Inicializa o atributo email.
        this.senha = obj.getSenha(); // Inicializa o atributo senha.
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet()); // Converte os perfis para inteiros.
        this.dataCriacao = obj.getDataCriacao();
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet()); // Converte os inteiros de volta para o enum Perfil.
    }
    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo()); // Adiciona um novo perfil ao conjunto de perfis.
    }
    public LocalDate getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
