package br.com.omni.projetos.controller.usuario;

import br.com.omni.projetos.dto.usuario.CadastrarRequest;
import br.com.omni.projetos.model.Departamento;
import br.com.omni.projetos.model.Usuario;
import br.com.omni.projetos.repository.DepartamentoRepository;
import br.com.omni.projetos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

/**
 * Controller class sing up user.
 * @author Gustavo Ridgue
 */

@Controller
public class CadastrarController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    /**
     * Method to return sing up page.
     * @param model Model - add attributes to template HTML
     * @return String - template HTML name
     **/
    @GetMapping("cadastrar")
    public String cadastrar(Model model) {
        List<Departamento> departamentos = departamentoRepository.findAll();
        model.addAttribute("departamentos", departamentos);

        return "usuario/cadastrar";
    }

    /**
     * Method to create user.
     * @param request CadastrarRequest - user data to create user
     * @param result BindingResult - validate if form has errors
     * @return String - template HTML name
     **/
    @PostMapping("cadastrado")
    public String cadastrado(CadastrarRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:usuario/cadastrar";
        }

        Optional<Departamento> dept = departamentoRepository.findById(request.getDepartamento());

        if (!dept.isPresent()) {
            return "redirect:usuario/cadastrar";
        }

        Departamento departamento = dept.get();
        Usuario usuario = request.toUsuario();
        usuario.setDepartamento(departamento);

        usuarioRepository.save(usuario);

//        redirectAttributes.addFlashAttribute("message", "Projeto '" + usuario.getNome() + "' criado com sucesso!");
        return "redirect:usuario/cadastrar";
    }
}
