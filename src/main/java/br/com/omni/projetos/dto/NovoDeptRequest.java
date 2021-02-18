package br.com.omni.projetos.dto;

import br.com.omni.projetos.model.Departamento;
import br.com.omni.projetos.model.Projeto;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotBlank;

public class NovoDeptRequest {
    @NotBlank (message = "Campo obrigatório!")
    private String nome;
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Departamento toDepartamento() {
        Departamento dept = new Departamento();
        dept.setNome(this.nome);
        dept.setSenha(this.senha);

        return dept;
    }
}
