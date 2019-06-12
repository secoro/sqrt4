package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sqrt4.mijninzet.model.Beschikbaarheid.Cohort;
import sqrt4.mijninzet.model.Beschikbaarheid.Week;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.repository.*;
import java.time.LocalDate;
import java.util.*;

@Controller
public class KoppelDocentenController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CohortRepository cohortRepository;
    @Autowired
    private VakdagdeelRespository vakdagdeelRespository;
    @Autowired
    private WeekRepository weekRepository;
    @Autowired
    private VakRepository vakRepository;
    @Autowired
    private DagRepository dagRepository;
    @Autowired
    private DagdeelRepository dagdeelRepository;

    @GetMapping("roosteraar/docenten-koppelen-kies-cohort")
    public String koppelDocenten(Model model) {
//        Calendar calendar = new GregorianCalendar();
//        Date trialTime = new Date();
//        calendar.setTime(trialTime);
        int huidigeJaar = LocalDate.now().getYear();
//        int huidigeWeek = calendar.get(Calendar.WEEK_OF_YEAR);
//
        List<Cohort> cohortList = cohortRepository.findAllByStartJaarIsGreaterThanEqual(huidigeJaar);
//        Predicate<Cohort> condition = cohort -> cohort.getStartJaar() == huidigeJaar && cohort.getStartWeek() <= huidigeWeek;
//        cohortList.removeIf(condition);

        model.addAttribute("cohortList", cohortList);
        return "roosteraar/docenten-koppelen-kies-cohort";
    }

    @GetMapping("roosteraar/docenten-koppelen-gekozen-cohort")
    public String docentenKoppelen(@RequestParam("cohortNaam") String cohortnaam,
                                   Model model) {

        Cohort cohort = cohortRepository.findByCohortNaam(cohortnaam);
        model.addAttribute("cohort", cohort);

        int index = 0;
        model.addAttribute("index", index);

        List<Week> weekList = cohort.getWeekList();
        model.addAttribute("weekList", weekList);



        List<User> docentList = userRepository.findAllByRolesContaining("DOCENT");
        model.addAttribute("docentList", docentList);
        return "roosteraar/docenten-koppelen-gekozen-cohort";
    }

}
