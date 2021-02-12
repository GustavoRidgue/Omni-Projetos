package br.com.omni.projetos.dto;

import br.com.omni.projetos.model.Departamento;
import br.com.omni.projetos.model.Projeto;
import com.sun.istack.NotNull;

import java.time.LocalDate;

public class NovoProjetoRequest {
    @NotNull // (message = "Campo nome do produto precisa ser preenchido*")
    private String nome;
    @NotNull // (message = "Campo descricao precisa ser preenchido*") @Max(250)
    private String departamento;
    @NotNull // (message = "Campo URL do produto deve ser preenchido*")
    private String situacaoAtual;
    @NotNull // (message = "Campo endereço da imagem deve ser preenchido*")
    private String situacaoDesejada;
    @NotNull // (message = "Campo endereço da imagem deve ser preenchido*")
    private String solucao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
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

    public Projeto toProjeto() {
//        Departamento departamento = new Departamento();
//        departamento.setNome(this.departamento);

        Projeto projeto = new Projeto();
        projeto.setNome(this.nome);
//        projeto.setDepartamento(departamento);
        projeto.setDataSolicitacao(LocalDate.now());
        projeto.setAnaliseSituacaoAtual(this.situacaoAtual);
        projeto.setDescricaoSituacaoDesejada(this.situacaoDesejada);
        projeto.setDescricaoSolucao(this.solucao);

        return projeto;
    }
}
