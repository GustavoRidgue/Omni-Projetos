package br.com.omni.projetos.controller;

import br.com.omni.projetos.dto.NovoProjetoRequest;
import br.com.omni.projetos.model.Departamento;
import br.com.omni.projetos.model.Projeto;
import br.com.omni.projetos.model.Regulatorio;
import br.com.omni.projetos.repository.DepartamentoRepository;
import br.com.omni.projetos.repository.ProjetoRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("projeto")
public class NovoController {
    @Autowired
    private ProjetoRepositoy projetoRepositoy;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping("criar")
    public String criar(Model model) {
        List<Departamento> departamentos = departamentoRepository.findAll();
        model.addAttribute("departamentos", departamentos);

        return "projeto/criar";
    }

    @PostMapping("novo")
    public String novo(NovoProjetoRequest request) {
        Optional<Departamento> dept = departamentoRepository.findById(request.getDepartamento());

        if (!dept.isPresent()) {
            return null;
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

        return "projeto/criar";
    }

}