package br.com.omni.projetos.dto.usuario;

import br.com.omni.projetos.model.Departamento;
import br.com.omni.projetos.model.Projeto;
import br.com.omni.projetos.model.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;

public class CadastrarRequest {
//    @NotBlank(message = "Campo obrigatório!")
    private String nome;
//    @NotBlank(message = "Campo obrigatório!")
    private String email;
//    @NotBlank (message = "Campo obrigatório!")
    private String senha;
//    @NotBlank (message = "Campo obrigatório!")
    private Long departamento;
//    @NotBlank (message = "Campo obrigatório!")
    private String telefone;
    private String foto;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Long departamento) {
        this.departamento = departamento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Usuario toUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome(this.nome);
        usuario.setEmail(this.email);
        usuario.setSenha(new BCryptPasswordEncoder().encode(this.senha));
        usuario.setTelefone(this.telefone);
        usuario.setFoto(this.foto);

        return usuario;
    }
}
