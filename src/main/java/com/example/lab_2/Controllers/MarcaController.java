package com.example.lab_2.Controllers;

import com.example.lab_2.entity.Marca;
import com.example.lab_2.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/marcas")
public class MarcaController {

    @Autowired
    MarcaRepository marcasRepository;

    @GetMapping("")
    public String listarMarcas(Model model) {
        List<Marca> listaMarca = marcasRepository.findAll();
        model.addAttribute("listaMarcas", listaMarca);
        return "/marcas/lista";
    }

    @GetMapping("/nuevo")
    public String newform() {
        return "/marcas/newform";
    }

    @PostMapping("/save")
    public String saveShip(Marca marca, RedirectAttributes a, Model model) {

        System.out.println(marca.getId());
        if (marca.getId() == 0) { //Hay que crear
            a.addFlashAttribute("msg", "0");
        } else { //Hay que actualizar
            a.addFlashAttribute("msg", "1");
        }
        marcasRepository.save(marca);
        return "redirect:/marcas";
    }

    @GetMapping("/editar")
    public String editarForm(@RequestParam("id") Integer id, Model model, RedirectAttributes a) {
        Optional<Marca> optionalMarca = marcasRepository.findById(id);
        if (optionalMarca.isPresent()) {
            Marca marca = optionalMarca.get();
            model.addAttribute("marca", marca);
            return "marcas/editar";
        } else {
            a.addFlashAttribute("msg", -1); //Ocurrio un error al momento de querer editar
            return "redirect:/marcas";
        }
    }

    @GetMapping("/borrar")
    public String borrar(@RequestParam("id") Integer id, RedirectAttributes a) {
        Optional<Marca> optionalMarca = marcasRepository.findById(id);
        if (optionalMarca.isPresent()) {
            a.addFlashAttribute("msg", "2"); //Marca borrada exitosamente
            marcasRepository.deleteById(id);
        } else {
            a.addFlashAttribute("msg", "-2"); //Ocurrio un error al momento de querer borrar
        }
        return "redirect:/marcas";
    }


}
