package br.com.omni.projetos.controller.departamento;

import br.com.omni.projetos.dto.departamento.NovoDeptRequest;
import br.com.omni.projetos.model.Departamento;
import br.com.omni.projetos.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/departamento")
public class NovoDeptController {
    @Autowired
    private DepartamentoRepository departamentoRepository;

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