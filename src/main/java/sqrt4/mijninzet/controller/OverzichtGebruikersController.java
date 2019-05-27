package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.repository.UserRepository;

import java.util.List;

@Controller
public class OverzichtGebruikersController extends AbstractController{

    @Autowired
    UserRepository userRepository;

    @GetMapping("/admin/overzicht-gebruikers")
    public String overzichtGebruikers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("gebruikers", users);
        return "overzicht-gebruikers";
    }

    @PostMapping("/admin/overzicht-gebruikers")
    public String verwijderGebruiker(@RequestParam("Verwijder") Long userId, Model model) {
        User activeUser = voegActiveUserToe();
        if(userId == activeUser.getId()){
            List<User> users = userRepository.findAll();
            model.addAttribute("gebruikers", users);
            return "overzicht-gebruikers";
        }
        userRepository.deleteById(userId);
        List<User> users = userRepository.findAll();
        model.addAttribute("gebruikers", users);
        return "overzicht-gebruikers";
    }
}
