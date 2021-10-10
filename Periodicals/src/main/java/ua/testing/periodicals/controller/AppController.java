package ua.testing.periodicals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.testing.periodicals.model.entity.Category;
import ua.testing.periodicals.model.entity.Periodical;
import ua.testing.periodicals.repository.CategoriesRepository;
import ua.testing.periodicals.repository.PeriodicalsRepository;
import ua.testing.periodicals.repository.UserRepository;
import ua.testing.periodicals.service.CategoriesService;
import ua.testing.periodicals.service.PeriodicalsService;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PeriodicalsRepository periodicalsRepo;

    @Autowired
    private PeriodicalsService periodicalsService;

    @Autowired
    private CategoriesRepository categoriesRepo;

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("")
    public String viewHomePage() {
        return "index.html";
    }

    @GetMapping("/login")
    public String get(Model model) {
        model.addAttribute("title", "Sign In form");
        return "login";
    }

    @GetMapping("/periodicals")
    public String listPeriodicals(Model model) {
        List<Periodical> listPeriodicals = periodicalsRepo.findAll();
        model.addAttribute("listPeriodicals", listPeriodicals);

        return "periodicals.html";
    }

    @GetMapping("/categories")
    public String listCategories(Model model) {
        List<Category> listCategories = categoriesRepo.findAll();
        model.addAttribute("listCategories", listCategories);

        return "categories.html";
    }

    @RequestMapping("/search/periodicals")
    public String searchPeriodicals(Model model, @Param("keyword") String keyword) {
        List<Periodical> listPeriodicals = periodicalsService.listAll(keyword);
        model.addAttribute("listPeriodicals", listPeriodicals);
        model.addAttribute("keyword", keyword);
        return "search/periodicals.html";
    }

    @GetMapping("/about")
    public String listAbout(Model model) {
        List<Periodical> listPeriodicals = periodicalsRepo.findAll();
        model.addAttribute("listPeriodicals", listPeriodicals);

        return "about.html";
    }
}