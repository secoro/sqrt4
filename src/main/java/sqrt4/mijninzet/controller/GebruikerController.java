package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sqrt4.mijninzet.model.Role;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.repository.RoleRepository;
import sqrt4.mijninzet.repository.UserRepository;

import java.util.List;

@RequestMapping("/admin")
@Controller
public class GebruikerController extends AbstractController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/nieuwe-gebruiker")
    public String nieuweGebruiker(Model model) {
        List<Role> rollen = roleRepository.findAll();
        model.addAttribute("roles", rollen);
        return "/admin/nieuwe-gebruiker";
    }

    @PostMapping("/nieuwe-gebruiker")
    public String nieuweGebruiker(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setRoles(user.getRoles().toUpperCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(1);
        userRepository.save(user);
        return "/admin/gebruiker-toegevoegd";
    }

    @GetMapping("/overzicht-gebruikers")
    public String overzichtGebruikers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("gebruikers", users);
        return "/admin/overzicht-gebruikers";
    }

    @PostMapping("/overzicht-gebruikers")
    public String verwijderGebruiker(@RequestParam(value = "Verwijder", required = false) Long userIdVerwijder,
                                     @RequestParam(value = "Wijzig", required = false) Long userIdWijzig,
                                     Model model) {
        User activeUser = voegActiveUserToe();
        if (userIdVerwijder != null) {
            if (userIdVerwijder == activeUser.getId()) {
                List<User> users = userRepository.findAll();
                model.addAttribute("gebruikers", users);
                return "/admin/overzicht-gebruikers";
            } else if (userIdVerwijder != activeUser.getId()) {
                userRepository.deleteById(userIdVerwijder);
                List<User> users = userRepository.findAll();
                model.addAttribute("gebruikers", users);
                return "/admin/overzicht-gebruikers";
            }
        } else if (userIdWijzig != null) {
            User user = userRepository.findUserById(userIdWijzig);
            model.addAttribute("gebruiker", user);
            List<Role> rollen = roleRepository.findAll();
            model.addAttribute("roles", rollen);
            return "/admin/wijzig-gebruiker";
        }
        return "/admin/overzicht-gebruikers";
    }


    @PostMapping("/wijzig-gebruiker")
    public String wijzigGebruiker(User user, Model model) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setRoles(user.getRoles().toUpperCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(1);
        userRepository.save(user);
        List<User> users = userRepository.findAll();
        model.addAttribute("gebruikers", users);
        return "/admin/overzicht-gebruikers";
    }
}