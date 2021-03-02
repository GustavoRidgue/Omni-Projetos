package br.com.omni.projetos.dto.departamento;

import javax.validation.constraints.NotBlank;

public class AtualizarDeptRequest {
    private Long id;
    private String nome;

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
}
