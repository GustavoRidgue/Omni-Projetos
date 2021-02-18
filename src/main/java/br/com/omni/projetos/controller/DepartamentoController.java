package br.com.omni.projetos.controller;

import br.com.omni.projetos.dto.NovoDeptRequest;
import br.com.omni.projetos.model.Departamento;
import br.com.omni.projetos.model.Projeto;
import br.com.omni.projetos.repository.DepartamentoRepository;
import br.com.omni.projetos.repository.ProjetoRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
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

    @GetMapping("criar")
    public String criar(NovoDeptRequest novoDeptRequest, Model model) {
//        model.addAttribute("msgCriado", "Todos departamentos:");
        return "departamento/criar";
    }

    @PostMapping("novo")
    public String novo(@Valid NovoDeptRequest request, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "departamento/criar";
        }

        Departamento dept = request.toDepartamento();

        if (dept.getSenha().equals("Omni2020")) {
            departamentoRepository.save(dept);
//            model.addAttribute("msgCriado", "Departamento " + dept.getNome() + " criado com sucesso!");
            return "redirect:todos";
        } else {
            model.addAttribute("erroSenha", "Senha inválida");
            return "departamento/criar";
        }
    }
}
