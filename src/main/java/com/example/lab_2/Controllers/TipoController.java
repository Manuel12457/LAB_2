package com.example.lab_2.Controllers;

import com.example.lab_2.entity.Marca;
import com.example.lab_2.entity.Tipo;
import com.example.lab_2.repository.MarcaRepository;
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
@RequestMapping("/tipo")
public class TipoController {

    @Autowired
    TipoRepository tipoRepository;

    @GetMapping("")
    public String listarTipos(Model model) {
        List<Tipo> listaTipo = tipoRepository.findAll();
        model.addAttribute("listaTipo", listaTipo);
        return "/tipos/lista";
    }

    @GetMapping("/nuevo")
    public String newform() {
        return "/tipos/newform";
    }

    @PostMapping("/save")
    public String saveTipo(Tipo tipo, RedirectAttributes a, Model model) {

        if (tipo.getId() == 0) { //Hay que crear
            a.addFlashAttribute("msg", "0");
        } else { //Hay que actualizar
            a.addFlashAttribute("msg", "1");
        }
        tipoRepository.save(tipo);
        return "redirect:/tipo";
    }

    @GetMapping("/editar")
    public String editarForm(@RequestParam("id") Integer id, Model model, RedirectAttributes a) {
        Optional<Tipo> optionalTipo = tipoRepository.findById(id);
        if (optionalTipo.isPresent()) {
            Tipo tipo = optionalTipo.get();
            model.addAttribute("tipo", tipo);
            return "tipos/editar";
        } else {
            a.addFlashAttribute("msg", -1); //Ocurrio un error al momento de querer editar
            return "redirect:/tipo";
        }
    }

    @GetMapping("/borrar")
    public String borrar(@RequestParam("id") Integer id, RedirectAttributes a) {
        Optional<Tipo> optionalTipo = tipoRepository.findById(id);
        if (optionalTipo.isPresent()) {
            a.addFlashAttribute("msg", "2"); //Marca borrada exitosamente
            tipoRepository.deleteById(id);
        } else {
            a.addFlashAttribute("msg", "-2"); //Ocurrio un error al momento de querer borrar
        }
        return "redirect:/tipo";
    }

}
