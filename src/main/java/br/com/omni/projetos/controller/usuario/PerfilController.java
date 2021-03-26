package br.com.omni.projetos.controller.usuario;

import br.com.omni.projetos.model.Usuario;
import br.com.omni.projetos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("usuario")
public class PerfilController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("perfil")
    public String perfil(Model model, Authentication authentication) {
        Usuario user = (Usuario) authentication.getPrincipal();
        Optional<Usuario> usuario = usuarioRepository.findById(user.getId());

        if (usuario.isPresent()) {
            Usuario usuarioAutenticado = usuario.get();
            model.addAttribute("usuario", usuarioAutenticado);
            return "usuario/perfil";
        }

        return "redirect:/home";
    }
}
