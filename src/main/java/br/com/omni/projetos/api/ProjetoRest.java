package br.com.omni.projetos.api;

import br.com.omni.projetos.model.Projeto;
import br.com.omni.projetos.model.Regulatorio;
import br.com.omni.projetos.repository.ProjetoRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoRest {

    @Autowired
    private ProjetoRepositoy projetoRepositoy;

    @GetMapping("todos")
    public List<Projeto> getPedidosAguardandoOfertas() {
        return projetoRepositoy.findAll();
    }

    @GetMapping("todos/{id}")
    public Optional<Projeto> getPedidosAguardandoOfertas(@PathVariable("id") Long id) {
        return projetoRepositoy.findById(id);
    }

    @GetMapping("regulatorio/{regulatorio}")
    public List<Projeto> regularoio(@PathVariable("regulatorio") String regulatorio) {
        try {
            return projetoRepositoy.findByRegulatorio(Regulatorio.valueOf(regulatorio.toUpperCase()));
        } catch (Exception e) {
            return projetoRepositoy.findAll();
        }
    }
}
