package br.com.omni.projetos.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dataSolicitacao;
    @ManyToOne
    private Departamento departamento;
    @Enumerated(EnumType.STRING)
    private Regulatorio regulatorio;
    private String analiseSituacaoAtual;
    private String descricaoSituacaoDesejada;
    private String descricaoSolucao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Regulatorio getRegulatorio() {
        return regulatorio;
    }

    public void setRegulatorio(Regulatorio regulatorio) {
        this.regulatorio = regulatorio;
    }

    public String getAnaliseSituacaoAtual() {
        return analiseSituacaoAtual;
    }

    public void setAnaliseSituacaoAtual(String analiseSituacaoAtual) {
        this.analiseSituacaoAtual = analiseSituacaoAtual;
    }

    public String getDescricaoSituacaoDesejada() {
        return descricaoSituacaoDesejada;
    }

    public void setDescricaoSituacaoDesejada(String descricaoSituacaoDesejada) {
        this.descricaoSituacaoDesejada = descricaoSituacaoDesejada;
    }

    public String getDescricaoSolucao() {
        return descricaoSolucao;
    }

    public void setDescricaoSolucao(String descricaoSolucao) {
        this.descricaoSolucao = descricaoSolucao;
    }
}
