package br.com.sistema.controller;

import br.com.sistema.model.Cargo;
import br.com.sistema.model.Projeto;
import br.com.sistema.service.CargoServiceImpl;
import br.com.sistema.service.FuncionarioServiceImpl;
import br.com.sistema.service.ProjetoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProjetoController {
    @Autowired
    ProjetoServiceImpl projetoService;

    @Autowired
    FuncionarioServiceImpl funcionarioService;

    @Autowired
    CargoServiceImpl cargoService;

    @GetMapping("/projeto/list")
    public String list(Model model){
        model.addAttribute("projetos", projetoService.findAll());
        return "projeto/list";
    }

    @GetMapping("/projeto/add")
    public String add(Model model){
        model.addAttribute("projeto", new Projeto());
        Cargo cargo = cargoService.findByNome("Gerente");
        model.addAttribute("gerentes", funcionarioService.findByCargo(cargo));
        return "projeto/add";
    }

    @GetMapping("/projeto/edit/{id}")
    public String add(@PathVariable Long id, Model model){
        model.addAttribute("projeto", projetoService.findById(id));
        Cargo cargo = cargoService.findByNome("Gerente");
        model.addAttribute("gerentes", funcionarioService.findByCargo(cargo));
        return "projeto/edit";
    }

//    @PostMapping("/projeto/save")
//    public String save(Projeto projeto, Model model){
//
//        String msgErro = projetoService.validarProjeto(projeto);
//        if (msgErro != null){
//            model.addAttribute("projeto", projeto);
//            model.addAttribute("erro", true);
//            model.addAttribute("Erro ao salvar projeto", msgErro);
//
//            if (projeto.getId() == null)return "projeto/add";
//            else return "projeto/edit";
//       }
//
//        if (projetoService.save(projeto)){
//            return "redirect:/projeto/list";
//        }else{
//
//            return "redirect:/projeto/list";
//        }
//    }

    @PostMapping("/projeto/save")
    public String save(Projeto projeto, Model model){

        //TODO: os alunos farão o validar

        if (projetoService.save(projeto)){
            return "redirect:/projeto/list";
        } else {
            //TODO: os alunos farão a validação
            return "redirect:/projeto/list";
        }
    }




    @GetMapping("/projeto/delete/{id}")
    public String delete(@PathVariable Long id, Projeto projeto, Model model){
        String msgErro= projetoService.validarProjeto(projeto);

        if(projetoService.deleteById(id)){
            return "redirect:/projeto/list";
        }else{
            model.addAttribute("projeto", projeto);
            model.addAttribute("erro", true);
            model.addAttribute("Erro ao deletar projeto", msgErro);
            return "redirect:projeto/list";
        }

    }

}
