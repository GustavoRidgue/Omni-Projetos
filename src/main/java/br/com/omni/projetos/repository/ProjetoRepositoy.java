package br.com.omni.projetos.repository;

import br.com.omni.projetos.model.Projeto;
import br.com.omni.projetos.model.Regulatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository to fetch data from Projeto table.
 * @author Gustavo Ridgue
 */
@Repository
public interface ProjetoRepositoy extends JpaRepository<Projeto, Long> {

    @Query(value = "SELECT * FROM projeto ORDER BY data_solicitacao DESC;", nativeQuery = true)
    List<Projeto> findAllBySolicitacaoDesc();

    List<Projeto> findByRegulatorio(Regulatorio regulatorio);
}
