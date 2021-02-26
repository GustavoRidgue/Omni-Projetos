package br.com.omni.projetos.repository;

import br.com.omni.projetos.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetoRepositoy extends JpaRepository<Projeto, Long> {

    List<Projeto> findByNome(String nome);
}
