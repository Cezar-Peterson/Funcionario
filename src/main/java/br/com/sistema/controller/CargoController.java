package br.com.sistema.controller;

import br.com.sistema.model.Cargo;
import br.com.sistema.model.Funcionario;
import br.com.sistema.service.CargoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CargoController {

    @Autowired
    CargoServiceImpl cargoService;

    @GetMapping("/cargo/list")
    public String list(Model model){
        model.addAttribute("cargos", cargoService.findAll());
        return "cargo/list";
    }

    @GetMapping("/cargo/add")
    public String add(Model model) {
        model.addAttribute("cargo", new Cargo());
        return "cargo/add";
    }

    @PostMapping("/cargo/save")
    public String save(Cargo cargo, Model model){
        String msgErro = cargoService.validarCargo(cargo);
        if (msgErro != null) {
            model.addAttribute("cargo", cargo);
            model.addAttribute("erro", true);
            model.addAttribute("erroMsg", msgErro);

            if (cargo.getId() == null) return "cargo/add";
            else return "cargo/edit";
        }
        //TODO:
        if (cargoService.save(cargo)) {
            return "redirect:/cargo/list";
        } else {
            model.addAttribute("cargo", cargo);
            model.addAttribute("erro", true);
            model.addAttribute("Erro ao salvar cargo", msgErro);
            return "cargo/add";
        }
    }

    @GetMapping("/cargo/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("cargo", cargoService.findById(id));
        return "cargo/edit";
    }

    @GetMapping("/cargo/delete/{id}")
    public String delete(@PathVariable Long id, Cargo cargo, Model model){
        String msgErro = cargoService.validarCargo(cargo);
            if (cargoService.deleteById(id)){
                return "redirect:/cargo/list";
            }else{
                model.addAttribute("cargo", cargo);
                model.addAttribute("erro", true);
                model.addAttribute("Erro ao deletar cargo", msgErro);
                return "redirect:/cargo/list";
            }

    }
}
