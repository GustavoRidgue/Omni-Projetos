package br.com.omni.projetos.repository;

import br.com.omni.projetos.model.Projeto;
import br.com.omni.projetos.model.Regulatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjetoRepositoy extends JpaRepository<Projeto, Long> {

    List<Projeto> findByNome(String nome);

    List<Projeto> findByRegulatorio(Regulatorio regulatorio);
}
