package douglas.com.helpdesk.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import douglas.com.helpdesk.models.Chamado;

public class ChamadoDTO implements Serializable{
    private static final long serialVersionUID = 1L; // Número de versão para controle na serialização.
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy") // Formato da data de criação (ex: 01/01/2023).
    private LocalDate dataAbetura = LocalDate.now();; // Data de abertura do chamado.
    @JsonFormat(pattern = "dd/MM/yyyy") // Formato da data de fechamento (ex: 01/01/2023).
    private LocalDate dataFechamento;
    private Integer status;
    private Integer prioridade;
    private String titulo;
    private String observacoes;
    private Integer tecnico;
    private Integer cliente;
    private String nomeTecnico;
    private String nomeCliente;
    public ChamadoDTO() {
        super(); // Chama o construtor da classe pai (Object).
    }
    public ChamadoDTO(Chamado obj) {
        this.id = obj.getId();
        this.dataAbetura = obj.getDataAbetura();
        this.dataFechamento = obj.getDataFechamento();
        this.status = obj.getStatus().getCodigo(); // Acessa o código do status através do objeto Chamado.
        this.prioridade = obj.getPrioridade().getCodigo(); // Acessa o código da prioridade através do objeto Chamado.
        this.titulo = obj.getTitulo();
        this.observacoes = obj.getObservacoes();
        this.tecnico = obj.getTecnico().getId(); // Acessa o ID do técnico através do objeto Chamado.
        this.cliente = obj.getCliente().getId(); // Acessa o ID do cliente através do objeto Chamado.
        this.nomeTecnico = obj.getTecnico().getNome(); // Acessa o nome do técnico através do objeto Chamado.
        this.nomeCliente = obj.getCliente().getNome(); // Acessa o nome do cliente através do objeto Chamado.
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public LocalDate getDataAbetura() {
        return dataAbetura;
    }
    public void setDataAbetura(LocalDate dataAbetura) {
        this.dataAbetura = dataAbetura;
    }
    public LocalDate getDataFechamento() {
        return dataFechamento;
    }
    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getPrioridade() {
        return prioridade;
    }
    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getObservacoes() {
        return observacoes;
    }
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    public Integer getTecnico() {
        return tecnico;
    }
    public void setTecnico(Integer tecnico) {
        this.tecnico = tecnico;
    }
    public Integer getCliente() {
        return cliente;
    }
    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }
    public String getNomeTecnico() {
        return nomeTecnico;
    }
    public void setNomeTecnico(String nomeTecnico) {
        this.nomeTecnico = nomeTecnico;
    }
    public String getNomeCliente() {
        return nomeCliente;
    }
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}
