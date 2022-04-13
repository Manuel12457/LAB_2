package com.example.lab_2.Controllers;

import com.example.lab_2.entity.Sede;
import com.example.lab_2.entity.Trabajador;
import com.example.lab_2.repository.SedeRepository;
import com.example.lab_2.repository.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sede")
public class SedeController {

    @Autowired
    SedeRepository sedeRepository;

    @Autowired
    TrabajadorRepository trabajadorRepository;

    @GetMapping("/listar")
    public String listarSedes(Model model){
        List<Sede> listaSedes = sedeRepository.findAll();
        model.addAttribute("listaSedes",listaSedes);
        return "sedes/lista";
    }

    @GetMapping("/newform")
    public String newformSedes(){
        return "sedes/newform";
    }

    @PostMapping("/save")
    public String saveSedes(@RequestParam("nombre") String nombre, Sede sede){
        sede.setNombreSede(nombre);
        sedeRepository.save(sede);
        return "redirect:/sede/listar";
    }

    @GetMapping("/editar")
    public String editarSedes(@RequestParam("id") int id, Model model){
        Optional<Sede> optionalSede = sedeRepository.findById(id);
        if (optionalSede.isPresent()) {
            Sede sede = optionalSede.get();
            model.addAttribute("sede", sede);
            List<Trabajador> trabajadorList = trabajadorRepository.findByIdsede(id);
            model.addAttribute("trabajadorList", trabajadorList);
            return "sedes/editar";
        } else {
            return "redirect:/sede/listar";
        }
    }

    @PostMapping("/edit")
    public String editAreas(Sede sede){
        sedeRepository.save(sede);
        return "redirect:/sede/listar";
    }

    @GetMapping("/borrar")
    public String borrarSedes(@RequestParam("id") int id){
        Optional<Sede> optionalTrabajador = sedeRepository.findById(id);
        if (optionalTrabajador.isPresent()) {
            sedeRepository.deleteById(id);
        }
        return "redirect:/sede/listar";
    }
}
