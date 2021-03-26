package br.com.omni.projetos.dto.departamento;

import br.com.omni.projetos.model.Departamento;

import javax.validation.constraints.NotBlank;

/**
 * Data transfer object to create department.
 * @author Gustavo Ridgue
 */
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


    /**
     * Method to return a new department by the given NovoDeptRequest class attributes.
     * @return Department - department
     **/
    public Departamento toDepartamento() {
        Departamento dept = new Departamento();
        dept.setNome(this.nome);
        dept.setSenha(this.senha);

        return dept;
    }
}
