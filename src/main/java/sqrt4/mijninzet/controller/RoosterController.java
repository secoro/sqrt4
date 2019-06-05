package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sqrt4.mijninzet.model.Beschikbaarheid.Cohort;
import sqrt4.mijninzet.model.Beschikbaarheid.Week;
import sqrt4.mijninzet.model.Vak;
import sqrt4.mijninzet.repository.CohortRepository;
import sqrt4.mijninzet.repository.VakRepository;
import sqrt4.mijninzet.repository.WeekRepository;

import java.util.List;

@Controller
public class RoosterController extends AbstractController{

    @Autowired
    CohortRepository cohortRepo;
    @Autowired
    VakRepository vakRepo;
    @Autowired
    WeekRepository weekRepo;

    @GetMapping("/manager/rooster-maken")
    public String maakRooster(Model model) {
        List<Cohort> cohorten = cohortRepo.findAll();
        model.addAttribute("cohorten", cohorten);
        return "rooster-maken";
    }

    @GetMapping("manager/rooster-maken-cohort-gekozen")
    public String wekenVanCohort(@RequestParam("cohortNaam") String cohortnaam,
                                 Model model) {
        Cohort cohort = cohortRepo.findByCohortNaam(cohortnaam);
        model.addAttribute("cohort", cohort);
        List<Vak> vakken = vakRepo.findAll();
        model.addAttribute("vakken", vakken);
        List<Week> weken = weekRepo.findWeeksByCohortId(cohort.getId());
        model.addAttribute("weken", weken);
        return "rooster-maken-cohort-gekozen";
    }
    @GetMapping("manager/rooster-karin")
    public String roosterKarin(@RequestParam("cohortNaam") String cohortnaam,
                                 Model model) {
        Cohort cohort = cohortRepo.findByCohortNaam(cohortnaam);
        model.addAttribute("cohort", cohort);
        List<Vak> vakken = vakRepo.findAll();
        model.addAttribute("vakken", vakken);
        List<Week> weken = weekRepo.findWeeksByCohortId(cohort.getId());
        model.addAttribute("weken", weken);
        return "rooster-karin";
    }

    @PostMapping("manager/rooster-karin")
    public String weekOpslaan(@RequestParam("cohortNaam") String cohortnaam,
                              @RequestParam("maOcht") String maOchtVak,
                              @RequestParam("diOcht") String diOchtVak,
                              @RequestParam("woOcht") String woOchtVak,
                              @RequestParam("doOcht") String doOchtVak,
                              @RequestParam("vrOcht") String vrOchtVak,
                              @RequestParam("maMid") String maMidVak,
                              @RequestParam("diMid") String diMidVak,
                              @RequestParam("woMid") String woMidVak,
                              @RequestParam("doMid") String doMidVak,
                              @RequestParam("vrMid") String vrMidVak,
                              @RequestParam("maAvo") String maAvoVak,
                              @RequestParam("diAvo") String diAvoVak,
                              @RequestParam("woAvo") String woAvoVak,
                              @RequestParam("doAvo") String doAvoVak,
                              @RequestParam("vrAvo") String vrAvoVak,
                              @ModelAttribute("cohort") Cohort cohort,
                              Model model) {
        Cohort cohort1 = cohortRepo.findByCohortNaam(cohortnaam);
        model.addAttribute(cohort1);
        Vak vakMaOcht = vakRepo.findByVakNaam(maOchtVak);
        Vak vakDiOcht = vakRepo.findByVakNaam(diOchtVak);
        Vak vakWoOcht = vakRepo.findByVakNaam(woOchtVak);
        Vak vakDoOcht = vakRepo.findByVakNaam(doOchtVak);
        Vak vakVrOcht = vakRepo.findByVakNaam(vrOchtVak);
        Vak vakMaMid = vakRepo.findByVakNaam(maMidVak);
        Vak vakDiMid = vakRepo.findByVakNaam(diMidVak);
        Vak vakWoMid = vakRepo.findByVakNaam(woMidVak);
        Vak vakDoMid = vakRepo.findByVakNaam(doMidVak);
        Vak vakVrMid = vakRepo.findByVakNaam(vrMidVak);
        Vak vakMaAvo = vakRepo.findByVakNaam(maAvoVak);
        Vak vakDiAvo = vakRepo.findByVakNaam(diAvoVak);
        Vak vakWoAvo = vakRepo.findByVakNaam(woAvoVak);
        Vak vakDoAvo = vakRepo.findByVakNaam(doAvoVak);
        Vak vakVrAvo = vakRepo.findByVakNaam(vrAvoVak);

        System.out.println(cohortnaam);
        System.out.println(cohort);
        return "rooster-karin";
    }
}
