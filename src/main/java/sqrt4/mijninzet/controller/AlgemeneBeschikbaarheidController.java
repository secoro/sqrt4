package sqrt4.mijninzet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlgemeneBeschikbaarheidController {

    @GetMapping("/algemene-beschikbaarheid")
    public String AlgemeneBeschikbaarheid() {

        return "algemene-beschikbaarheid";
    }
}
