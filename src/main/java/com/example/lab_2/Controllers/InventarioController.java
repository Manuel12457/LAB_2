package com.example.lab_2.Controllers;

import com.example.lab_2.entity.Inventario;
import com.example.lab_2.entity.Marca;
import com.example.lab_2.repository.InventarioRepository;
import com.example.lab_2.repository.MarcaRepository;
import com.example.lab_2.repository.SedeRepository;
import com.example.lab_2.repository.TipoRepository;
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
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    InventarioRepository inventarioRepository;

    @Autowired
    MarcaRepository marcaRepository;

    @Autowired
    SedeRepository sedeRepository;

    @Autowired
    TipoRepository tipoRepository;

    @GetMapping("")
    public String listarInventarios(Model model) {
        List<Inventario> listaInventario = inventarioRepository.findAll();
        model.addAttribute("listaInventario", listaInventario);
        return "/inventario/lista";
    }

    @GetMapping("/nuevo")
    public String newform(Model model) {

        model.addAttribute("listaSedes", sedeRepository.findAll());
        model.addAttribute("listaMarca", marcaRepository.findAll());
        model.addAttribute("listaTipo", tipoRepository.findAll());
        return "/inventario/newform";
    }

    @PostMapping("/save")
    public String saveInventario(Inventario inventario, RedirectAttributes a, Model model) {

        if (inventario.getId() == 0) { //Hay que crear
            a.addFlashAttribute("msg", "0");
        } else { //Hay que actualizar
            a.addFlashAttribute("msg", "1");
        }
        inventarioRepository.save(inventario);
        return "redirect:/inventario";
    }

    @GetMapping("/editar")
    public String editarForm(@RequestParam("id") Integer id, Model model, RedirectAttributes a) {
        Optional<Inventario> optionalInventario = inventarioRepository.findById(id);
        if (optionalInventario.isPresent()) {
            Inventario inventario = optionalInventario.get();
            model.addAttribute("inventario", inventario);
            model.addAttribute("listaSedes", sedeRepository.findAll());
            model.addAttribute("listaMarca", marcaRepository.findAll());
            model.addAttribute("listaTipo", tipoRepository.findAll());
            return "inventario/editar";
        } else {
            a.addFlashAttribute("msg", -1); //Ocurrio un error al momento de querer editar
            return "redirect:/inventario";
        }
    }

    @GetMapping("/borrar")
    public String borrar(@RequestParam("id") Integer id, RedirectAttributes a) {
        Optional<Inventario> optionalInventario = inventarioRepository.findById(id);
        if (optionalInventario.isPresent()) {
            a.addFlashAttribute("msg", "2"); //Marca borrada exitosamente
            inventarioRepository.deleteById(id);
        } else {
            a.addFlashAttribute("msg", "-2"); //Ocurrio un error al momento de querer borrar
        }
        return "redirect:/inventario";
    }

}
