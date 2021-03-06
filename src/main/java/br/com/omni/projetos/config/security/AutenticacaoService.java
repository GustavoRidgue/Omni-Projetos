package br.com.omni.projetos.config.security;

import br.com.omni.projetos.model.Usuario;
import br.com.omni.projetos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * API to return all projects.
 * @author Gustavo Ridgue
 */
@Service
public class AutenticacaoService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Method to return user by the given username.
     * @param username String - Username to load user
     * @return UserDetails - Provides core user information
     **/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Dados Inválidos"));
    }
}
