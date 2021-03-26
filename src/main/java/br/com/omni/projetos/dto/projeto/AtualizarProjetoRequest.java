package br.com.omni.projetos.dto.projeto;

import br.com.omni.projetos.model.Projeto;
import javax.validation.constraints.NotBlank;

/**
 * Data transfer object to update project.
 * @author Gustavo Ridgue
 */
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

    /**
     * Method to return a new project by the given AtualizarProjetoRequest class attributes.
     * @return Projeto - project
     **/
    public Projeto toProjeto() {
        Projeto projeto = new Projeto();
        projeto.setNome(this.nome);
        projeto.setAnaliseSituacaoAtual(this.situacaoAtual);
        projeto.setDescricaoSituacaoDesejada(this.situacaoDesejada);
        projeto.setDescricaoSolucao(this.solucao);

        return projeto;
    }
}
