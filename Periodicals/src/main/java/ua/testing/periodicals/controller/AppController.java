package ua.testing.periodicals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String listPeriodicalsAll(Model model) {
        /*
        List<Periodical> listPeriodicals = periodicalsRepo.findAll();
        model.addAttribute("listPeriodicals", listPeriodicals);

        return "periodicals.html";
        */
        //return "redirect:/periodicals/1";
        return listPeriodicalsSortingPager(model, 1, "name", "asc");
    }

    @RequestMapping("/periodicals/{pageNum}")
    public String listPeriodicalsSortingPager(Model model,
                           @PathVariable(name = "pageNum") int pageNum,
                           @Param("sortField") String sortField,
                           @Param("sortDir") String sortDir) {

        Page<Periodical> page = periodicalsService.listAll(pageNum, sortField, sortDir);

        List<Periodical> listPeriodicals = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

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