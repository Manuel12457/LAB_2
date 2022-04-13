package com.example.lab_2.Controllers;


import com.example.lab_2.entity.Sede;
import com.example.lab_2.repository.SedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.lab_2.entity.Trabajador;
import com.example.lab_2.repository.TrabajadorRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import java.util.List;
import java.util.Optional;
@Controller
@RequestMapping("/trabajador")
public class TrabajadorController {

    @Autowired
    TrabajadorRepository trabajadorRepository;

    @Autowired
    SedeRepository sedeRepository;

    @GetMapping("/listar")
    public String listarTrabajador(Model model) {
        List<Trabajador> trabajadorList = trabajadorRepository.findAll();
        model.addAttribute("trabajadorList", trabajadorList);
        return "/trabajadores/lista";
    }

    @GetMapping("/newform")
    public String newform(Model model) {
        List<Sede> listaSedes = sedeRepository.findAll();
        model.addAttribute("listaSedes",listaSedes);
        return "trabajadores/newform";
    }

    @PostMapping("/save")
    public String saveTrabajador(RedirectAttributes a, Trabajador trabajador) {
        Optional<Trabajador> optionalTrabajador = trabajadorRepository.findById(trabajador.getId());
        if (!optionalTrabajador.isPresent() ) { //Hay que crear
            a.addFlashAttribute("msg", "0");
        } else { //Hay que actualizar
            a.addFlashAttribute("msg", "1");
        }
        trabajadorRepository.save(trabajador);
        return "redirect:/trabajador/listar";
    }



    @GetMapping("/editar")
    public String editarForm(@RequestParam("id") String id, Model model) {
        Optional<Trabajador> optionalTrabajador = trabajadorRepository.findById(id);
        if (optionalTrabajador.isPresent()) {
            Trabajador trabajador = optionalTrabajador.get();
            model.addAttribute("trabajador", trabajador);
            List<Sede> listaSedes = sedeRepository.findAll();
            model.addAttribute("listaSedes",listaSedes);
            return "trabajadores/editar";
        } else {
            return "redirect:/trabajador/listar";
        }
    }

    @GetMapping("/borrar")
    public String borrar(@RequestParam("id") String id, RedirectAttributes a) {
        Optional<Trabajador> optionalTrabajador = trabajadorRepository.findById(id);
        if (optionalTrabajador.isPresent()) {
            a.addFlashAttribute("msg", "2"); //Trabajador borrada exitosamente
            trabajadorRepository.deleteById(id);
        } else {
            a.addFlashAttribute("msg", "-2"); //Ocurrio un error al momento de querer borrar
        }
        return "redirect:/trabajador/listar";
    }

}
