package br.com.omni.projetos.dto;

import br.com.omni.projetos.model.Departamento;
import br.com.omni.projetos.model.Projeto;
import br.com.omni.projetos.model.Regulatorio;
import com.sun.istack.NotNull;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class NovoProjetoRequest {
    @NotNull // (message = "Campo nome do produto precisa ser preenchido*")
    private String nome;
    @NotNull // (message = "Campo descricao precisa ser preenchido*") @Max(250)
    private Long departamento;
    @NotNull // (message = "Campo URL do produto deve ser preenchido*")
    private String situacaoAtual;
    @NotNull // (message = "Campo endereço da imagem deve ser preenchido*")
    private String situacaoDesejada;
    @NotNull // (message = "Campo endereço da imagem deve ser preenchido*")
    private String solucao;
    @NotNull // (message = "Campo endereço da imagem deve ser preenchido*")
    private boolean regulatorio;

//    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Long departamento) {
        this.departamento = departamento;
    }

    public String getSituacaoAtual() {
        return situacaoAtual;
    }

    public void setSituacaoAtual(String situacaoAtual) {
        this.situacaoAtual = situacaoAtual;
    }

    public String getSituacaoDesejada() {
        return situacaoDesejada;
    }

    public void setSituacaoDesejada(String situacaoDesejada) {
        this.situacaoDesejada = situacaoDesejada;
    }

    public String getSolucao() {
        return solucao;
    }

    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }

    public boolean isRegulatorio() {
        return regulatorio;
    }

    public void setRegulatorio(boolean regulatorio) {
        this.regulatorio = regulatorio;
    }

    public Projeto toProjeto() {
        Projeto projeto = new Projeto();
        projeto.setNome(this.nome);
        projeto.setAnaliseSituacaoAtual(this.situacaoAtual);
        projeto.setDescricaoSituacaoDesejada(this.situacaoDesejada);
        projeto.setDescricaoSolucao(this.solucao);

        return projeto;
    }
}
