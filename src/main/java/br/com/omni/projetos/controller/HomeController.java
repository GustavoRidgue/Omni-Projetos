package br.com.omni.projetos.controller;

import br.com.omni.projetos.model.Departamento;
import br.com.omni.projetos.model.Projeto;
import br.com.omni.projetos.repository.DepartamentoRepository;
import br.com.omni.projetos.repository.ProjetoRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("home")
public class HomeController {
    @Autowired
    private ProjetoRepositoy projetoRepositoy;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @GetMapping
    public String home(Model model, Principal principal) {
        List<Projeto>      projetos      = projetoRepositoy.findAll();
        model.addAttribute("projetos", projetos);

        return "home";
    }
}
