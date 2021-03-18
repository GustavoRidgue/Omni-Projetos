package br.com.omni.projetos.controller.usuario;

import br.com.omni.projetos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("usuario")
public class PerfilController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("perfil")
    public String perfil() {
        return "usuario/perfil";
    }
}
