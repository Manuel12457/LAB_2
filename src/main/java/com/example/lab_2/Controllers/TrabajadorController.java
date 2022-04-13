package com.example.lab_2.Controllers;


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

import java.util.List;
import java.util.Optional;
@Controller
@RequestMapping("/trabajador")
public class TrabajadorController {

    @Autowired
    TrabajadorRepository trabajadorRepository;

    @GetMapping("/listar")
    public String listarTrabajador(Model model) {
        List<Trabajador> trabajadorList = trabajadorRepository.findAll();
        model.addAttribute("trabajadorList", trabajadorList);
        return "trabajador/lista";
    }

    @GetMapping("/newform")
    public String newform() {
        return "trabajador/newForm";
    }

    @PostMapping("/save")
    public String saveTrabajador(Trabajador trabajador, RedirectAttributes attributes) {
        trabajadorRepository.save(trabajador);
        attributes.addFlashAttribute("msg","Trabajador creado exitosamente");
        return "redirect:/trabajador/listar";
    }

    @PostMapping("/update")
    public String updateTrabajador(Trabajador trabajadorForm) {
        Optional<Trabajador> optionalTrabajador = trabajadorRepository.findById(trabajadorForm.getId());
        if (optionalTrabajador.isPresent()) {
            Trabajador TrabajadorFromDb = optionalTrabajador.get();
            TrabajadorFromDb.setIdsede(trabajadorForm.getIdsede());
            trabajadorRepository.save(TrabajadorFromDb);
        }
        return "redirect:/trabajador/listar";
    }

    @GetMapping("/editar")
    public String editarForm(@RequestParam("id") String id, Model model) {
        Optional<Trabajador> optionalTrabajador = trabajadorRepository.findById(id);
        if (optionalTrabajador.isPresent()) {
            Trabajador trabajador = optionalTrabajador.get();
            model.addAttribute("trabajador", trabajador);
            return "trabajador/editForm";
        } else {
            return "redirect:/trabajador/listar";
        }
    }

    @GetMapping("/borrar")
    public String borrar(@RequestParam("id") String id) {
        Optional<Trabajador> optionalTrabajador = trabajadorRepository.findById(id);
        if (optionalTrabajador.isPresent()) {
            trabajadorRepository.deleteById(id);
        }
        return "redirect:/trabajador/listar";
    }

}
