package douglas.com.helpdesk;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import douglas.com.helpdesk.enums.Perfil;

public abstract class Pessoa {
    protected Integer id; // Identificador único da pessoa.
    protected String nome; // Nome da pessoa.
    protected String cpf; // CPF da pessoa (Cadastro de Pessoa Física).
    protected String email; // Email da pessoa.
    protected String senha; // Senha da pessoa para autenticação.
    protected Set<Integer> perfis = new HashSet<>(); // Conjunto de perfis associados à pessoa.
    protected LocalDate dataCriacao = LocalDate.now(); // Data de criação com valor padrão como a data atual.
    // Construtor padrão
    public Pessoa() {
        super(); // Chamada ao construtor da classe pai (no caso, Object).
        addPerfil(Perfil.CLIENTE);
    }
    
    // Construtor com parâmetros
    public Pessoa(Integer id, String nome, String cpf,String email, String senha) {
        super();
        this.id = id; // Inicializa o atributo id com o valor do parâmetro.
        this.nome = nome; // Inicializa o atributo nome.
        this.cpf = cpf; // Inicializa o atributo cpf.
        this.email = email;
        this.senha = senha; // Inicializa o atributo senha.
        addPerfil(Perfil.CLIENTE);
    }

    public Integer getId() {
        return id; // Retorna o id da pessoa.
    }

    public void setId(Integer id) {
        this.id = id; // Define o id da pessoa.
    }

    public String getNome() {
        return nome; // Retorna o nome da pessoa.
    }

    public void setNome(String nome) {
        this.nome = nome; // Define o nome da pessoa.
    }

    public String getCpf() {
        return cpf; // Retorna o CPF da pessoa.
    }

    public void setCpf(String cpf) {
        this.cpf = cpf; // Define o CPF da pessoa.
    }
    public String getEmail() {
        return email; // Retorna o email da pessoa.
    }
    public void setEmail(String email) {
        this.email = email; // Define o email da pessoa.
    }
    public String getSenha() {
        return senha; // Retorna a senha da pessoa.
    }

    public void setSenha(String senha) {
        this.senha = senha; // Define a senha da pessoa.
    }

    public Set<Perfil> getPerfis() {
        // Converte os códigos dos perfis armazenados no Set<Integer>
        // para os valores enumerados de Perfil (Perfil.toEnum()).
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        // Adiciona um perfil ao conjunto de perfis da pessoa.
        // O perfil é armazenado pelo seu código (Integer).
        this.perfis.add(perfil.getCodigo());
    }

    public LocalDate getDataCriacao() {
        return dataCriacao; // Retorna a data de criação da pessoa.
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao; // Define a data de criação da pessoa.
    }





    
}
