package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import sqrt4.mijninzet.model.Vak;
import sqrt4.mijninzet.repository.VakRepository;

import java.util.List;

@Controller
public class VakController {

    @Autowired
    VakRepository vakRepository;

    @GetMapping("/coordinator/vak-aanmaken")
    public String vakAanmaken() {
        return "coordinator/vak-aanmaken";
    }

    @GetMapping("/coordinator/vak-overzicht")
    public String vakToegevoegd(@ModelAttribute Vak vak,
                                Model model){
        vakRepository.save(vak);
        List<Vak> vakken = vakRepository.findAll();
        model.addAttribute("vakken", vakken);
        return "coordinator/vak-overzicht";
    }
}
