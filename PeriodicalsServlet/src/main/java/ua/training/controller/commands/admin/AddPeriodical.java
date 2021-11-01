package ua.training.controller.commands.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.controller.commands.Registration;
import ua.training.model.entity.Periodical;
import ua.training.model.service.PeriodicalsService;

import javax.servlet.http.HttpServletRequest;

public class AddPeriodical implements Command {

    private final PeriodicalsService periodicalService;
    private final Logger logger = LogManager.getLogger(Registration.class.getName());

    public AddPeriodical(PeriodicalsService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        if( name == null || name.equals("") || description == null || description.equals("")  ) {
            logger.info("AddPeriodical form opened");
            return "/WEB-INF/admin/add_periodical.jsp";
        }

        Periodical periodical = new Periodical();
        periodical.setName(request.getParameter("name"));
        periodical.setDescription(request.getParameter("description"));
        periodical.setCatId(Long.parseLong(request.getParameter("category")));
        periodical.setPrice(Double.parseDouble(request.getParameter("price")));

        logger.info("Periodical adding request : " + "(" + request.getParameterMap().toString() + ")");
        int result = periodicalService.create(periodical);
        if (result < 1) {
            RuntimeException runtimeException = new RuntimeException("User exists");
            request.setAttribute("exception", runtimeException);
            return "/WEB-INF/error.jsp";
        }

        logger.info("Periodical adding (name, description) : "
                + "(" + periodical.getName() + ", " + periodical.getDescription() + ")");

        return "redirect:/app/admin/periodicals";
    }
}
