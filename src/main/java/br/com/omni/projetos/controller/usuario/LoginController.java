package br.com.omni.projetos.controller.usuario;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller class to login user.
 * @author Gustavo Ridgue
 */
@Controller
@RequestMapping("login")
public class LoginController {

    /**
     * Method to return login page.
     * @return String - template HTML name
     **/
    @GetMapping
    public String login() {
        return "usuario/login";
    }

}
