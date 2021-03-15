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
import org.springframework.data.domain.PageRequest;
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

@Controller
@RequestMapping("home")
public class HomeController {
    @Autowired
    private ProjetoRepositoy projetoRepositoy;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    //ctrl alt c cria variavel
    //ctrl alt v cria variavel
    //ctrl alt o elimina imports
    //@Transient faz com que atributo n sera criado do banco de dados

    @GetMapping
    public String home(@RequestParam(required = false) Long id, Model model,
                       @PageableDefault(sort = "id", direction = Sort.Direction.ASC,
                               page = 0, size = 5) Pageable pageable) {
        if (id == null) {
            Page<Projeto> projetos = projetoRepositoy.findAll(pageable);

            List<Pags> pags = new ArrayList<>();

            for (int i = 0; i < projetos.getTotalPages(); i++) {
                pags.add(new Pags(i));
            }

            model.addAttribute("subtitulo", "Projetos");
            model.addAttribute("projetos", projetos);

            model.addAttribute("totalPag", projetos.getTotalPages());
            model.addAttribute("pags", pags);
            model.addAttribute("numberPag", pageable.getPageNumber());

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


    @GetMapping("/regulatorio/{regulatorio}")
    public String regularoio(@PathVariable("regulatorio") String regulatorio, Model model) {
        List<Projeto> byRegulatorio = projetoRepositoy.findByRegulatorio(Regulatorio.valueOf(regulatorio.toUpperCase()));
        String status = String.valueOf(Regulatorio.valueOf(regulatorio.toUpperCase()));

        model.addAttribute("regulatorio", status);
        model.addAttribute("subtitulo", "Projetos (regulatório " + status.toLowerCase() + ")");
        model.addAttribute("projetos", byRegulatorio);

        return "home";
    }

//    @GetMapping("/dados/{page}")
//    public String homePageable(@PathVariable("page") int page, Model model) {
////        PageRequest paginacao = PageRequest.of(page, 5, Sort.unsorted());
////        Page<Projeto> byRegulatorio = projetoRepositoy.findAll(paginacao);
//
//        List<Projeto> byRegulatorio = projetoRepositoy.findAllByPageable(page);
//        Integer qntdLinhas = projetoRepositoy.findRows();
//
//        model.addAttribute("regulatorio", "status");
//        model.addAttribute("subtitulo", "Projetos (regulatório)");
//        model.addAttribute("projetos", byRegulatorio);
//
//        return "home";
//    }

    @GetMapping("/dataSolicitacaoDesc")
    public String dataSolicitacaoDesc(Model model) {
        List<Projeto> byRegulatorio = projetoRepositoy.findAllBySolicitacaoDesc();

        model.addAttribute("status", true);
        model.addAttribute("subtitulo", "Projetos (Ordenado por mais recentes)");
        model.addAttribute("projetos", byRegulatorio);

        return "home";
    }

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
