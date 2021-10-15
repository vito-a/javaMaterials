package ua.testing.periodicals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import ua.testing.periodicals.model.dao.DBException;
import ua.testing.periodicals.model.entity.Periodical;
import ua.testing.periodicals.model.entity.User;
import ua.testing.periodicals.repository.PeriodicalsRepository;
import ua.testing.periodicals.repository.UserRepository;
import ua.testing.periodicals.service.PeriodicalsService;
import ua.testing.periodicals.service.UsersService;

import java.sql.Date;
import java.time.LocalDate;

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

        Periodical periodical = periodicalService.get(periodicalId);
        mav.addObject("periodical", periodical);

        return mav;
    }

    @RequestMapping("/periodical/subscribe/{user_id}/{periodical_id}")
    public String subscribePeriodical(@PathVariable(name = "user_id") Long userId,
                                      @PathVariable(name = "periodical_id") Long periodicalId) throws DBException {
        logger.info("subscribePeriodical");

        User user = (User) userRepo.getUserByUserId(userId);
        Periodical periodical = (Periodical) periodicalsRepo.getPeriodicalByPeriodicalId(periodicalId);

        logger.info("periodical ID ==> " + periodicalId);

        if (user == null) {
            throw new DBException("To subscribe, please sign in!");
        }

        if (userService.checkSubscription(userId, periodicalId)) {
            throw new DBException("You are already subscribed");
        }

        if (user.getBalance() < periodical.getPrice()) {
            throw new DBException("You don't have enough account balance");
        }

        userService.updateBalance(user, periodical.getPrice());

        long millis = System.currentTimeMillis();
        java.sql.Date startDate = new java.sql.Date(millis);
        logger.info("subscription startDate = " + startDate);

        LocalDate endDate = LocalDate.now().plusYears(1);
        logger.info("subscription endDate = " + endDate);

        // Long currentUserID = user.getId();

        periodicalService.subscribe(periodicalId, userId, startDate, Date.valueOf(endDate));

        return "redirect:/periodicals";
    }

    @RequestMapping("/periodical/delete/{id}")
    public String deletePeriodical(@PathVariable(name = "id") Long periodicalId) {
        periodicalService.delete(periodicalId);

        return "redirect:/periodicals";
    }
}