package br.com.omni.projetos.controller;

import br.com.omni.projetos.dto.atualizar.AtualizarProjetoRequest;
import br.com.omni.projetos.model.Departamento;
import br.com.omni.projetos.model.Projeto;
import br.com.omni.projetos.repository.DepartamentoRepository;
import br.com.omni.projetos.repository.ProjetoRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("home")
public class HomeController {
    @Autowired
    private ProjetoRepositoy projetoRepositoy;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @GetMapping
    public String home(Model model) {
        List<Projeto>      projetos      = projetoRepositoy.findAll();
        model.addAttribute("projetos", projetos);

        return "home";
    }

    @GetMapping("/detalhes/{id}")
    public String detalhe(@PathVariable("id") Long id, Model model) {
        Optional<Projeto> projeto = projetoRepositoy.findById(id);
        Projeto proj = projeto.get();
        model.addAttribute("projeto", proj);

        return "projeto/detalhes";
    }

    @PostMapping("/deletar/{id}")
    @Transactional
    public String deletar(@PathVariable("id") Long id, Model model) {
        Optional<Projeto> projeto = projetoRepositoy.findById(id);

        if(projeto.isPresent()) {
            projetoRepositoy.deleteById(id);
            List<Projeto>      projetos      = projetoRepositoy.findAll();
            model.addAttribute("projetos", projetos);
            return "home";
        }

        return "home";
    }

    @PutMapping("/atualizar/{id}")
    @Transactional
    public String atualizar(@PathVariable("id") Long id, @Valid AtualizarProjetoRequest atualizarProjeto, Model model) {
        Optional<Projeto> optional = projetoRepositoy.findById(id);

        if(optional.isPresent()) {
            Projeto proj = optional.get();
            model.addAttribute("projeto", proj);

            Projeto projeto = atualizarProjeto.atualizar(id, projetoRepositoy);
            model.addAttribute("projetos", optional);

            return "home";
        }

        return "home";
    }
}
