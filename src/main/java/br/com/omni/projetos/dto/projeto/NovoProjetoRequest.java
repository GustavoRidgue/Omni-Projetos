package br.com.omni.projetos.dto.projeto;

import br.com.omni.projetos.model.Projeto;

import javax.validation.constraints.NotBlank;

public class NovoProjetoRequest {
    @NotBlank(message = "Campo obrigatório!")
    private String nome;
    private Long departamento;
    @NotBlank (message = "Campo obrigatório!")
    private String situacaoAtual;
    @NotBlank (message = "Campo obrigatório!")
    private String situacaoDesejada;
    @NotBlank (message = "Campo obrigatório!")
    private String solucao;
    private boolean regulatorio;

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
