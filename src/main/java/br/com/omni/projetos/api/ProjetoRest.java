package br.com.omni.projetos.api;

import br.com.omni.projetos.model.Projeto;
import br.com.omni.projetos.model.Regulatorio;
import br.com.omni.projetos.repository.ProjetoRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * API for return projects data.
 * @author Gustavo Ridgue
 */

@RestController
@RequestMapping("/api/projetos")
public class ProjetoRest {

    @Autowired
    private ProjetoRepositoy projetoRepositoy;

    /**
     * Method for return all projects.
     * @return List<Projeto> - all projects
     **/
    @GetMapping("todos")
    public List<Projeto> getProjetos() {
        return projetoRepositoy.findAll();
    }

    /**
     * Method return project indicated by id in parameter.
     * @param id Long - project id
     * @return Projeto - projeto
     **/
    @GetMapping("todos/{id}")
    public Optional<Projeto> getProjetosId(@PathVariable("id") Long id) {
        return projetoRepositoy.findById(id);
    }

    /**
     * Method return project by regulatory.
     * @param regulatorio String - is or is not regulatory
     * @return List<Projeto> - projects that is or is not regulatory
     **/
    @GetMapping("regulatorio/{regulatorio}")
    public List<Projeto> regularoio(@PathVariable("regulatorio") String regulatorio) {
        try {
            return projetoRepositoy.findByRegulatorio(Regulatorio.valueOf(regulatorio.toUpperCase()));
        } catch (Exception e) {
            return projetoRepositoy.findAll();
        }
    }
}


//ctrl alt c cria variavel
//ctrl alt v cria variavel
//ctrl alt o elimina imports
//@Transient faz com que atributo n sera criado do banco de dados