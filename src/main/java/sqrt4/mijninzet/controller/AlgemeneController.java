package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sqrt4.mijninzet.model.Beschikbaarheid.Week;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.model.Vak;
import sqrt4.mijninzet.repository.IncidentregistratieRepository;
import sqrt4.mijninzet.repository.VoorkeurenRepository;
import sqrt4.mijninzet.repository.WeekRepository;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ajax")
public class AlgemeneController {

    public static final int AANTAL_WEKEN_IN_JAAR = 52;

    @Autowired
    private VoorkeurenRepository voorkeurenRepository;
    @Autowired
    private WeekRepository weekRepo;

    @Autowired
    private IncidentregistratieRepository repoIncident;

    @RequestMapping(value = "/voorkeuren/{user}/{vak}", method = RequestMethod.POST)
    public Integer testRest(@PathVariable User user, @PathVariable Vak vak) {
        Integer preference = 0;
        try {
            Integer databaseVoorkeur = voorkeurenRepository.findVoorkeurByVakAndUser(vak, user).getVoorkeurGebruiker();
            if (databaseVoorkeur == null) {
                return 0;
            } else {
                return databaseVoorkeur;
            }
        } catch (NullPointerException e) {
        }
        return preference;
    }

//    @RequestMapping(value = "/incidenten-beheer/catch", method = RequestMethod.POST)
//    public @ResponseBody List<Incident> haalLijstOp(){
//        List<Incident> incidentList = repoIncident.findAllByStatusIsContaining("in behandeling");
//
//        return incidentList;}

//    @RequestMapping(value = "/ajax/roosteraar/docent-koppelen/{cohortnummer}/{weeknummer}", method = RequestMethod.POST)
//    public Week haalWeekOp(@PathVariable Cohort cohort, @PathVariable Week weeknummer) {
//
//    }

    @RequestMapping(value = "/{user}", method = RequestMethod.POST)
    public String testRest2(@PathVariable User user) {
        return "";
    }

    @RequestMapping(value = "/roosteraar/docent-koppelen-gekozen-cohort/{weekId}", method = RequestMethod.POST)
    public Week getAantalWekenGeselecteerdeCohort(@PathVariable int weekId) {
        Week week = null;


        return week;


    }
    //haalt de vakken op die al voor die week zijn ogeslagen (James en Karin)
    @RequestMapping(value = "/manager/rooster-maken-cohort-gekozen-karin/{week}", method = RequestMethod.GET)
    public List<String> vakAlOpgeslagen(@PathVariable Week week) {
        String maOcht = week.getMaandag().getOchtend().getVak().getVakNaam();
        String maMid = week.getMaandag().getMiddag().getVak().getVakNaam();
        String maAvo = week.getMaandag().getAvond().getVak().getVakNaam();
        String diOcht = week.getDinsdag().getOchtend().getVak().getVakNaam();
        String diMid = week.getDinsdag().getMiddag().getVak().getVakNaam();
        String diAvo = week.getDinsdag().getAvond().getVak().getVakNaam();
        String woOcht = week.getWoensdag().getOchtend().getVak().getVakNaam();
        String woMid = week.getWoensdag().getMiddag().getVak().getVakNaam();
        String woAvo = week.getWoensdag().getAvond().getVak().getVakNaam();
        String doOcht = week.getDonderdag().getOchtend().getVak().getVakNaam();
        String doMid = week.getDonderdag().getMiddag().getVak().getVakNaam();
        String doAvo = week.getDonderdag().getAvond().getVak().getVakNaam();
        String vrOcht = week.getVrijdag().getOchtend().getVak().getVakNaam();
        String vrMid = week.getVrijdag().getMiddag().getVak().getVakNaam();
        String vrAvo = week.getVrijdag().getAvond().getVak().getVakNaam();
        List<String> list = Arrays.asList(maOcht, maMid, maAvo, diOcht, diMid, diAvo, woOcht, woMid, woAvo, doOcht, doMid, doAvo, vrOcht, vrMid, vrAvo);
        return list;
    }
}
