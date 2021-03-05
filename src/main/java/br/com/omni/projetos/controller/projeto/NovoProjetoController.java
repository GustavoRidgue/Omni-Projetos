package br.com.omni.projetos.controller.projeto;

import br.com.omni.projetos.dto.projeto.NovoProjetoRequest;
import br.com.omni.projetos.model.Departamento;
import br.com.omni.projetos.model.Projeto;
import br.com.omni.projetos.model.Regulatorio;
import br.com.omni.projetos.repository.DepartamentoRepository;
import br.com.omni.projetos.repository.ProjetoRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("projeto")
public class NovoProjetoController {
    @Autowired
    private ProjetoRepositoy projetoRepositoy;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping("criar")
    public String criar(Model model, NovoProjetoRequest request) {
        List<Departamento> departamentos = departamentoRepository.findAll();
        model.addAttribute("departamentos", departamentos);

        return "projeto/criar";
    }

    @PostMapping("novo")
    public String novo(@Valid NovoProjetoRequest request, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departamentos", departamentoRepository.findAll());
            return "projeto/criar";
        }

        Optional<Departamento> dept = departamentoRepository.findById(request.getDepartamento());

        if (!dept.isPresent()) {
            return "projeto/criar";
        }

        Departamento departamento = dept.get();
        Projeto projeto = request.toProjeto();
        projeto.setRegulatorio(Regulatorio.SIM);
        projeto.setDataSolicitacao(LocalDate.now());
        projeto.setDepartamento(departamento);

        if (request.isRegulatorio()) {
            projeto.setRegulatorio(Regulatorio.SIM);
        } else {
            projeto.setRegulatorio(Regulatorio.NÃO);
        }

        projetoRepositoy.save(projeto);

        redirectAttributes.addFlashAttribute("message", "Projeto '" + projeto.getNome() + "' criado com sucesso!");
        return "redirect:/home";
    }

}