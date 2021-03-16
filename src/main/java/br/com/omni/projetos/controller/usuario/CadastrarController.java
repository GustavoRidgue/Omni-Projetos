package br.com.omni.projetos.controller.usuario;

import br.com.omni.projetos.model.Departamento;
import br.com.omni.projetos.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("cadastrar")
public class CadastrarController {
    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping
    public String cadastrar(Model model) {
        List<Departamento> departamentos = departamentoRepository.findAll();
        model.addAttribute("departamentos", departamentos);

        return "usuario/cadastrar";
    }
}
