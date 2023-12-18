package org.example.ejerciciospringboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.time.LocalDateTime;
import java.util.HashMap;

@Controller
public class MiPrimerControlador {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("hora", LocalDateTime.now());
        model.asMap().put("mapa", new HashMap<String, String>() {
            {
                put("nombre", "Alejandro");
                put("apellidos", "Nebot Flores");
                put("email", "alenebflo@alu.edu.gva.es");
            }
        });
        return "index";
    }
}
