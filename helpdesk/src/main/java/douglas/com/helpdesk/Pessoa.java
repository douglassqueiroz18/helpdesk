package douglas.com.helpdesk;
// Declaração do pacote onde a classe está localizada.

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
// Importação das bibliotecas necessárias. 
// LocalDate é usado para lidar com datas, 
// HashSet e Set para manipular coleções sem duplicatas, 
// e Collectors para realizar operações de transformação em streams.
public abstract class Pessoa {
    // Classe abstrata Pessoa, ou seja, não pode ser instanciada diretamente.
    // Serve como base para outras classes que herdarão seus atributos e métodos.

    // Atributos privados (acessados por getters e setters)
       // Métodos getters e setters para encapsulamento dos atributos.
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

    public void addPerfis(Perfil perfil) {
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

    // Comentário explicativo:
    // Todos os atributos da classe têm o modificador de acesso `protected`.
    // Isso significa que as subclasses (filhas) podem acessar diretamente esses atributos.
    protected Integer id; // Identificador único da pessoa.
    protected String nome; // Nome da pessoa.
    protected String cpf; // CPF da pessoa (Cadastro de Pessoa Física).
    protected String senha; // Senha da pessoa para autenticação.
    protected Set<Integer> perfis = new HashSet<>(); // Conjunto de perfis associados à pessoa.
    protected LocalDate dataCriacao = LocalDate.now(); // Data de criação com valor padrão como a data atual.

    // Construtor padrão
    public Pessoa() {
        super(); // Chamada ao construtor da classe pai (no caso, Object).
    }

    // Construtor com parâmetros
    public Pessoa(Integer id, String nome, String cpf, String senha, Set<Perfil> perfis, LocalDate dataCriacao) {
        super();
        this.id = id; // Inicializa o atributo id com o valor do parâmetro.
        this.nome = nome; // Inicializa o atributo nome.
        this.cpf = cpf; // Inicializa o atributo cpf.
        this.senha = senha; // Inicializa o atributo senha.
        this.dataCriacao = dataCriacao; // Inicializa a data de criação.
    }


    
}
