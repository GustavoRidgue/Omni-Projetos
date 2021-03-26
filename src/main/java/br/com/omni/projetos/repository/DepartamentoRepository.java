package br.com.omni.projetos.repository;

import br.com.omni.projetos.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository to fetch data from Departamento table.
 * @author Gustavo Ridgue
 */
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    Optional<Departamento> findByNome(String departamento);
}
