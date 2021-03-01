package br.com.omni.projetos.controller.departamento;

import br.com.omni.projetos.dto.NovoDeptRequest;
import br.com.omni.projetos.dto.atualizar.AtualizarDeptRequest;
import br.com.omni.projetos.dto.atualizar.AtualizarProjetoRequest;
import br.com.omni.projetos.model.Departamento;
import br.com.omni.projetos.model.Projeto;
import br.com.omni.projetos.repository.DepartamentoRepository;
import br.com.omni.projetos.repository.ProjetoRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/departamento")
public class DepartamentoController {
    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping("/todos")
    public String home(String nome, Model model) {
        if (nome == null) {
            List<Departamento> departamentos = departamentoRepository.findAll();
            model.addAttribute("departamentos", departamentos);

            return "departamento/todos";
        } else {
            Optional<Departamento> dept = departamentoRepository.findByNome(nome);
            if (dept.isPresent()) {
                Departamento departamento = dept.get();
                model.addAttribute("departamentos", departamento);

                return "departamento/todos";
            }

            return "redirect:/departamento/todos";
        }
    }

    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable("id") Long id, Model model) {
        Optional<Departamento> dept = departamentoRepository.findById(id);

        if (dept.isPresent()) {
            Departamento departamento = dept.get();
            model.addAttribute("departamento", departamento);

            return "departamento/alterar";
        }

        return "redirect:departamento/todos";
    }

    @PostMapping("atualizado")
    @Transactional
    public String atualizado(AtualizarDeptRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/departamento/todos";
        }

        Departamento departamento = departamentoRepository.getOne(request.getId());
        departamento.setNome(request.getNome());

        return "redirect:/departamento/todos";
    }
}
