package br.com.sistema.controller;

import br.com.sistema.model.Funcionario;
import br.com.sistema.service.FuncionarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FuncionarioController {

    @Autowired
    FuncionarioServiceImpl funcionarioService;
    //FuncionarioRepository funcionarioRepository;

    @GetMapping("/funcionario/list")
    public String list(Model model){
        model.addAttribute("funcionarios", funcionarioService.findAll());
        System.out.println(funcionarioService.findAll());
        return "funcionario/list";
    }

    @GetMapping("/funcionario/add")
    public String add(Model model){
        model.addAttribute("funcionario", new Funcionario());
        return "funcionario/add";
    }

    @PostMapping("/funcionario/save")
    public String save(Funcionario funcionario, Model model){

        if (funcionarioService.findByEmail(funcionario.getEmail()) != null){
            model.addAttribute("funcionario", funcionario);
            model.addAttribute("erro", true);
            model.addAttribute("erroMsg", "O e-mail já foi cadastrado!");
            return "funcionario/add";
        }

        if (funcionarioService.save(funcionario)){
            return "redirect:/funcionario/list";
        }else{
            model.addAttribute("funcionario", funcionario);
            //model.addAttribute("erro", true);
            return "funcionario/add";
        }


    }


    //Battistella n fez ainda
//
//    @PostMapping("/funcionario/save")
//    public String save(Funcionario funcionario) {
//        try {
//            if(funcionario != null) {
//                funcionarioService.save(funcionario);
//            }
//        } catch (Exception e) {
//            System.out.println("Erro ao salvar:" + e.getMessage());
//        }
//        return "redirect:/funcionario/list";
//
//    }

//
//    @GetMapping("/funcionario/delete/{id}")
//    public String deleteFuncionario(@PathVariable long id) {
//        try {
//
//            funcionarioService.deleteById(id);
//
//        } catch (Exception e) {
//            System.out.println("Erro ao deletar: " + e.getMessage());
//        }
//        return "redirect:/funcionario/list";
//    }
}
