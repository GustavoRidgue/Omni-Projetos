package br.com.omni.projetos.controller;

import br.com.omni.projetos.dto.NovoProjetoRequest;
import br.com.omni.projetos.model.Projeto;
import br.com.omni.projetos.model.Regulatorio;
import br.com.omni.projetos.repository.ProjetoRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("projeto")
public class NovoController {
    @Autowired
    private ProjetoRepositoy projetoRepositoy;

    @GetMapping("criar")
    public String criar() {
        return "projeto/criar";
    }

    @PostMapping("novo")
    public String novo(NovoProjetoRequest request) {
        Projeto projeto = request.toProjeto();
        projeto.setRegulatorio(Regulatorio.SIM);
        projetoRepositoy.save(projeto);

        return "projeto/criar";
    }

}