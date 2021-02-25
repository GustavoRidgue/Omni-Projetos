package br.com.omni.projetos.dto.deletar;

import br.com.omni.projetos.model.Projeto;

import javax.validation.constraints.NotBlank;

public class DeletarProjetoRequest {
    private Long id;
    private String senha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    //    public Projeto toProjeto() {
//        Projeto projeto = new Projeto();
//        projeto.setNome(this.nome);
//        projeto.setAnaliseSituacaoAtual(this.situacaoAtual);
//        projeto.setDescricaoSituacaoDesejada(this.situacaoDesejada);
//        projeto.setDescricaoSolucao(this.solucao);
//
//        return projeto;
//    }
}
