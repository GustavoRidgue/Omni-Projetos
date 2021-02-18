package br.com.omni.projetos.controller;

import br.com.omni.projetos.model.Departamento;
import br.com.omni.projetos.model.Projeto;
import br.com.omni.projetos.repository.DepartamentoRepository;
import br.com.omni.projetos.repository.ProjetoRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/departamento")
public class DepartamentoController {
    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping("/todos")
    public String home(Model model, Principal principal) {
        List<Departamento> departamentos = departamentoRepository.findAll();
        model.addAttribute("departamentos", departamentos);

        return "departamento/todos";
    }
}
