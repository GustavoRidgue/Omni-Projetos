package br.com.omni.projetos.repository;

import br.com.omni.projetos.model.Projeto;
import br.com.omni.projetos.model.Regulatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjetoRepositoy extends JpaRepository<Projeto, Long> {

    @Query(value = "SELECT * FROM projeto ORDER BY data_solicitacao DESC;", nativeQuery = true)
    List<Projeto> findAllBySolicitacaoDesc();

    List<Projeto> findByRegulatorio(Regulatorio regulatorio);
}
