package br.com.omni.projetos.repository;

import br.com.omni.projetos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository to fetch data from Usuario table.
 * @author Gustavo Ridgue
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
