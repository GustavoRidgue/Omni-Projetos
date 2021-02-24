package br.com.omni.projetos.dto.atualizar;

import br.com.omni.projetos.model.Projeto;
import br.com.omni.projetos.repository.ProjetoRepositoy;

import javax.validation.constraints.NotBlank;

public class AtualizarProjetoRequest {
    private Long id;
    @NotBlank(message = "Campo obrigatório!")
    private String nome;
    @NotBlank (message = "Campo obrigatório!")
    private String situacaoAtual;
    @NotBlank (message = "Campo obrigatório!")
    private String situacaoDesejada;
    @NotBlank (message = "Campo obrigatório!")
    private String solucao;

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

//    public Projeto atualizar(Long id, ProjetoRepositoy projetoRepositoy) {
//        Projeto projeto = projetoRepositoy.getOne(id);
//        projeto.setNome(this.nome);
//        projeto.setAnaliseSituacaoAtual(this.situacaoAtual);
//        projeto.setDescricaoSituacaoDesejada(this.situacaoDesejada);
//        projeto.setDescricaoSolucao(this.solucao);
//
//        return projeto;
//    }

    public Projeto toProjeto() {
        Projeto projeto = new Projeto();
        projeto.setNome(this.nome);
        projeto.setAnaliseSituacaoAtual(this.situacaoAtual);
        projeto.setDescricaoSituacaoDesejada(this.situacaoDesejada);
        projeto.setDescricaoSolucao(this.solucao);

        return projeto;
    }
}
