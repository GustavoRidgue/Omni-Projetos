package br.com.omni.projetos.dto.projeto;

import br.com.omni.projetos.model.Projeto;

import javax.validation.constraints.NotBlank;

public class DeletarProjetoRequest {
    private Long id;
    private String nome;
    private String projeto;

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

    public String getProjeto() {
        return projeto;
    }

    public void setProjeto(String projeto) {
        this.projeto = projeto;
    }
}
