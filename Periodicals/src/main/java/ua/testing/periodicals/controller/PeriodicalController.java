package ua.testing.periodicals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import ua.testing.periodicals.model.entity.Periodical;
import ua.testing.periodicals.repository.PeriodicalsRepository;
import ua.testing.periodicals.repository.UserRepository;
import ua.testing.periodicals.model.constants.Constants;
import ua.testing.periodicals.model.entity.User;
import ua.testing.periodicals.service.PeriodicalsService;

@Controller
public class PeriodicalController {

    @Autowired
    private PeriodicalsRepository periodicalsRepo;

    @Autowired
    private PeriodicalsService service;

    @RequestMapping("/new")
    public String showNewPeriodicalForm(Model model) {
        Periodical periodical = new Periodical();
        model.addAttribute("periodical", periodical);

        return "periodical/new_periodical.html";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePeriodical(@ModelAttribute("periodical") Periodical periodical) {
        service.save(periodical);

        return "redirect:/";
    }
    
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditPeriodicalForm(@PathVariable(name = "id") Long periodicalId) {
        ModelAndView mav = new ModelAndView("periodical/edit_periodical.html");

        Periodical periodical = service.get(periodicalId);
        mav.addObject("periodical", periodical);

        return mav;
    }	
    
    @RequestMapping("/delete/{id}")
    public String deletePeriodical(@PathVariable(name = "id") Long periodicalId) {
        service.delete(periodicalId);

        return "redirect:/";
    }
}