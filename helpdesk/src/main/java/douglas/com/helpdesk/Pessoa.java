package douglas.com.helpdesk;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import douglas.com.helpdesk.enums.Perfil;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity // Indica que esta classe é uma entidade do banco de dados (uma tabela).
public abstract class Pessoa implements Serializable{// "Serializable" permite que o objeto seja salvo em arquivos ou trafegado em redes.
    private static final long serialVersionUID = 1L; // Número de versão para controle na serialização.

    @Id // Marca este campo como a chave primária (identificador único) da tabela.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O valor do ID é gerado automaticamente pelo banco de dados.
    protected Integer id; // Número único que identifica cada pessoa no sistema.
    protected String nome; // Armazena o nome da pessoa (ex: "João Silva").

    @Column(unique = true) // Garante que não existirão dois CPFs iguais no banco.
    protected String cpf; // Cadastro de Pessoa Física (documento único brasileiro).

    @Column(unique = true) // Garante que cada e-mail seja único no sistema.
    protected String email;  // E-mail da pessoa (ex: "joao@email.com").
    protected String senha; // Senha criptografada para login no sistema

    @ElementCollection(fetch = FetchType.EAGER)  // Lista de perfis carregada imediatamente quando a pessoa é consultada.
    @CollectionTable(name = "PERFIS") // Nome da tabela que armazena os perfis.
    protected Set<Integer> perfis = new HashSet<>(); // Conjunto de papéis que a pessoa tem (ex: Cliente, Admin).
    
    @JsonFormat(pattern = "dd/MM/yyyy") // Formato da data de criação (ex: 01/01/2023).
    protected LocalDate dataCriacao = LocalDate.now();// Data em que o cadastro foi criado (automático: data atual).
    // Construtor padrão (executado quando uma nova Pessoa é criada)
    public Pessoa() {
        super();  // Chama o construtor da classe pai (Object).
        addPerfil(Perfil.CLIENTE); // Por padrão, toda pessoa começa com o perfil "Cliente".
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
