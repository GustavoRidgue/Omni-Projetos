package br.com.omni.projetos.controller.departamento;

import br.com.omni.projetos.dto.departamento.AtualizarDeptRequest;
import br.com.omni.projetos.model.Departamento;
import br.com.omni.projetos.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Controller class to list and alter departments.
 * @author Gustavo Ridgue
 */

@Controller
@RequestMapping("/departamento")
public class DepartamentoController {
    @Autowired
    private DepartamentoRepository departamentoRepository;

    /**
     * Method to return all departments or a department by the given name.
     * @param nome String - department name (optional)
     * @param model Model - add attributes to template HTML
     * @return String - template HTML name
     **/
    @GetMapping("/todos")
//    @Cacheable(value = "departamento")
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

    /**
     * Method to get alter department page by the given id.
     * @param id Long - department id
     * @param model Model - add attributes to template HTML
     * @return String - template HTML name
     **/
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

    /**
     * Method to edit department.
     * @param request AtualizarDeptRequest - data to update department
     * @param result BindingResult - validate if form has errors
     * @return String - template HTML name
     **/
    @PostMapping("atualizado")
    @Transactional
    public String atualizado(AtualizarDeptRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/departamento/alterar";
        }

        Departamento departamento = departamentoRepository.getOne(request.getId());
        departamento.setNome(request.getNome());

        return "redirect:/departamento/todos";
    }
}
