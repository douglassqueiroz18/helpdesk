package douglas.com.helpdesk;

import java.io.ObjectInputFilter.Status;
import java.time.LocalDate;

import douglas.com.helpdesk.enums.Prioridade;

public class Chamado{
    private Integer id;
    private LocalDate dataAbetura = LocalDate.now();
    private LocalDate dataFechamento;
    private Status status;
    private Prioridade prioridade;
    private String titulo;
    private String observacoes;
    private Tecnico tecnico;
    private Cliente cliente;

    public Chamado() {
        super(); // Chama o construtor da classe pai (Object)
    }
    public Chamado(Integer id, LocalDate dataAbetura, LocalDate dataFechamento, Status status, Prioridade prioridade,
            String titulo, String observacoes, Tecnico tecnico, Cliente cliente) {
        super(); // Chama o construtor da classe pai (Object)
        this.id = id; // Inicializa o atributo id com o valor do parâmetro.
        this.dataAbetura = dataAbetura; // Inicializa a data de abertura do chamado.
        this.dataFechamento = dataFechamento; // Inicializa a data de fechamento do chamado.
        this.status = status; // Inicializa o status do chamado.
        this.prioridade = prioridade; // Inicializa a prioridade do chamado.
        this.titulo = titulo; // Inicializa o título do chamado.
        this.observacoes = observacoes; // Inicializa as observações do chamado.
        this.tecnico = tecnico; // Inicializa o técnico responsável pelo chamado.
        this.cliente = cliente; // Inicializa o cliente associado ao chamado.
    }
    public Integer getId() {
        return id; // Retorna o id do chamado.
    }
    public void setId(Integer id) {
        this.id = id; // Define o id do chamado.
    }
    public LocalDate getDataAbetura() {
        return dataAbetura; // Retorna a data de abertura do chamado.
    }
    public void setDataAbetura(LocalDate dataAbetura) {
        this.dataAbetura = dataAbetura; // Define a data de abertura do chamado.
    }
    public LocalDate getDataFechamento() {
        return dataFechamento; // Retorna a data de fechamento do chamado.
    }
    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento; // Define a data de fechamento do chamado.
    }
    public Status getStatus() {
        return status; // Retorna o status do chamado.
    }
    public void setStatus(Status status) {
        this.status = status; // Define o status do chamado.
    }
    public Prioridade getPrioridade() {
        return prioridade; // Retorna a prioridade do chamado.
    }
    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade; // Define a prioridade do chamado.
    }
    public String getTitulo() {
        return titulo; // Retorna o título do chamado.
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo; // Define o título do chamado.
    }
    public String getObservacoes() {
        return observacoes; // Retorna as observações do chamado.
    }
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes; // Define as observações do chamado.
    }
    public Tecnico getTecnico() {
        return tecnico; // Retorna o técnico responsável pelo chamado.
    }
    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico; // Define o técnico responsável pelo chamado.
    }
    public Cliente getCliente() {
        return cliente; // Retorna o cliente associado ao chamado.
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente; // Define o cliente associado ao chamado.
    }

}
