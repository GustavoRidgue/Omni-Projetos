package br.com.omni.projetos.controller.projeto;

import br.com.omni.projetos.controller.Pags;
import br.com.omni.projetos.dto.projeto.AtualizarProjetoRequest;
import br.com.omni.projetos.dto.projeto.DeletarProjetoRequest;
import br.com.omni.projetos.model.Projeto;
import br.com.omni.projetos.model.Regulatorio;
import br.com.omni.projetos.repository.DepartamentoRepository;
import br.com.omni.projetos.repository.ProjetoRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller class to list, edit and delete projects.
 * @author Gustavo Ridgue
 */

@Controller
@RequestMapping("home")
public class HomeController {
    @Autowired
    private ProjetoRepositoy projetoRepositoy;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    /**
     * Method to return all projects by pagination or a project by the given id.
     * @param id Long - project id (optional)
     * @param pageable Pageable - pageable to control projects pagination
     * @param model Model - add attributes to template HTML
     * @return String - template HTML name
     **/
    @GetMapping
    public String home(@RequestParam(required = false) Long id,
                       @PageableDefault(sort = "id", direction = Sort.Direction.ASC,
                               page = 0, size = 5) Pageable pageable, Model model) {
        if (id == null) {
            Page<Projeto> projetos = projetoRepositoy.findAll(pageable);

            List<Integer> pags = new ArrayList<>();

            for (int i = 0; i < projetos.getTotalPages(); i++) {
                pags.add(1);
            }

            model.addAttribute("subtitulo", "Projetos");
            model.addAttribute("projetos", projetos);

            model.addAttribute("totalPag", projetos.getTotalPages());
            model.addAttribute("numberPag", pageable.getPageNumber());
            model.addAttribute("pags", pags);

            return "home";
        } else {
            Optional<Projeto> projetos = projetoRepositoy.findById(id);

            if (projetos.isPresent()) {
                Projeto projeto = projetos.get();
                model.addAttribute("projetos", projeto);
                model.addAttribute("subtitulo", "Projeto (id " + id + ")");

                return "home";
            }

            return "redirect:home";
        }
    }

    /**
     * Method to return all projects by the given regulatory.
     * @param regulatorio String - covert to a Regulatorio enum
     * @param model Model - add attributes to template HTML
     * @return String - template HTML name
     **/
    @GetMapping("/regulatorio/{regulatorio}")
    public String regularoio(@PathVariable("regulatorio") String regulatorio, Model model) {
        List<Projeto> byRegulatorio = projetoRepositoy.findByRegulatorio(Regulatorio.valueOf(regulatorio.toUpperCase()));
        String status = String.valueOf(Regulatorio.valueOf(regulatorio.toUpperCase()));

        model.addAttribute("regulatorio", status);
        model.addAttribute("subtitulo", "Projetos (regulatório " + status.toLowerCase() + ")");
        model.addAttribute("projetos", byRegulatorio);

        return "home";
    }

    /**
     * Method to return all projects by dataSolicitacao decreasing.
     * @param model Model - add attributes to template HTML
     * @return String - template HTML name
     **/
    @GetMapping("/dataSolicitacaoDesc")
    public String dataSolicitacaoDesc(Model model) {
        List<Projeto> byRegulatorio = projetoRepositoy.findAllBySolicitacaoDesc();

        model.addAttribute("status", true);
        model.addAttribute("subtitulo", "Projetos (Ordenado por mais recentes)");
        model.addAttribute("projetos", byRegulatorio);

        return "home";
    }

    /**
     * Method to get project details page by the given id.
     * @param id Long - project id
     * @param model Model - add attributes to template HTML
     * @return String - template HTML name
     **/
    @GetMapping("/detalhes/{id}")
    public String detalhe(@PathVariable("id") Long id, Model model) {
        Optional<Projeto> projeto = projetoRepositoy.findById(id);

        if (projeto.isPresent()) {
            Projeto proj = projeto.get();
            model.addAttribute("projeto", proj);

            return "projeto/detalhes";
        }

        return "redirect:home";
    }

    /**
     * Method to get alter project page by the given id.
     * @param id Long - project id
     * @param model Model - add attributes to template HTML
     * @return String - template HTML name
     **/
    @GetMapping("/alterar/{id}")
    public String paginaAtualizar(@PathVariable("id") Long id, Model model) {
        Optional<Projeto> optional = projetoRepositoy.findById(id);
        
        if(optional.isPresent()) {
            Projeto projeto = optional.get();
            model.addAttribute("projeto", projeto);
            return "projeto/alterar";
        }

        return "redirect:home";
    }

    /**
     * Method to edit project.
     * @param request AtualizarProjetoRequest - project data to update project
     * @param result BindingResult - validate if form has errors
     * @param redirectAttributes RedirectAttributes - add flash attributes to template HTML
     * @return String - template HTML name
     **/
    @PostMapping("alterado")
    @Transactional
    public String atualizado(@Valid AtualizarProjetoRequest request, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "redirect:alterar/" + request.getId();
        }

        Projeto projeto = projetoRepositoy.getOne(request.getId());

        projeto.setId(request.getId());
        projeto.setNome(request.getNome());
        projeto.setAnaliseSituacaoAtual(request.getSituacaoAtual());
        projeto.setDescricaoSituacaoDesejada(request.getSituacaoDesejada());
        projeto.setDescricaoSolucao(request.getSolucao());

        redirectAttributes.addFlashAttribute("message", "Projeto '" + projeto.getNome() + "' alterado com sucesso!");
//        redirectAttributes.addFlashAttribute("fadeClass", "fadeOut");
        return "redirect:/home";
    }

    /**
     * Method to delete project.
     * @param request DeletarProjetoRequest - verify if name field equals project.name
     * @param redirectAttributes RedirectAttributes - add flash attributes to template
     * @return String - template HTML name
     **/
    @PostMapping("deletado")
    @Transactional
    public String deletar(DeletarProjetoRequest request, RedirectAttributes redirectAttributes) {
        Optional<Projeto> projeto = projetoRepositoy.findById(request.getId());

        if(projeto.isPresent() && request.getProjeto().equals(request.getNome())) {
            projetoRepositoy.deleteById(request.getId());

            redirectAttributes.addFlashAttribute("message", "Projeto '" + request.getNome() + "' deletado com sucesso!");

            return "redirect:/home";
        }

        return "redirect:alterar/" + request.getId();
    }
}
