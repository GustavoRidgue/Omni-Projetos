//package br.com.omni.projetos.controller;
//
//import br.com.omni.projetos.dto.NovoDeptRequest;
//import br.com.omni.projetos.dto.NovoProjetoRequest;
//import br.com.omni.projetos.model.Departamento;
//import br.com.omni.projetos.model.Projeto;
//import br.com.omni.projetos.model.Regulatorio;
//import br.com.omni.projetos.repository.DepartamentoRepository;
//import br.com.omni.projetos.repository.ProjetoRepositoy;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//@RequestMapping("departamento")
//public class NovoDeptController {
//    @Autowired
//    private DepartamentoRepository departamentoRepository;
//
//    @GetMapping("criar")
//    public String criar(Model model) {
//        return "departamento/criar";
//    }
//
//    @PostMapping("novo")
//    public String novo(NovoDeptRequest request) {
//        Departamento dept = request.toDepartamento();
//
//        if (dept.getSenha().equals("Omni2020")) {
//            departamentoRepository.save(dept);
//            return "departamento/todos";
//        } else {
//            return "departamento/criar";
//        }
//    }
//
//}