package ua.testing.periodicals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import ua.testing.periodicals.model.entity.Periodical;
import ua.testing.periodicals.repository.PeriodicalsRepository;
import ua.testing.periodicals.repository.UserRepository;
import ua.testing.periodicals.service.PeriodicalsService;
import ua.testing.periodicals.service.UsersService;

import java.util.Optional;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class PeriodicalController {
    @Autowired
    private PeriodicalsRepository periodicalsRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PeriodicalsService periodicalService;

    @Autowired
    private UsersService userService;

    private static final Logger logger = LoggerFactory.getLogger(PeriodicalController.class);

    @RequestMapping("/periodical/new")
    public String showNewPeriodicalForm(Model model) {
        Periodical periodical = new Periodical();
        model.addAttribute("periodical", periodical);

        return "periodical/new_periodical.html";
    }
    
    @RequestMapping(value = "/periodical/save", method = RequestMethod.POST)
    public String savePeriodical(@ModelAttribute("periodical") Periodical periodical) {
        periodicalService.save(periodical);

        return "redirect:/periodicals";
    }
    
    @RequestMapping("/periodical/edit/{id}")
    public ModelAndView showEditPeriodicalForm(@PathVariable(name = "id") Long periodicalId) {
        ModelAndView mav = new ModelAndView("periodical/edit_periodical.html");
        Optional<Periodical> periodical = periodicalService.get(periodicalId);
        periodical.ifPresent(value -> mav.addObject("periodical", value));
        return mav;
    }

    @RequestMapping("/periodical/delete/{id}")
    public String deletePeriodical(@PathVariable(name = "id") Long periodicalId) {
        periodicalService.delete(periodicalId);

        return "redirect:/periodicals";
    }
}