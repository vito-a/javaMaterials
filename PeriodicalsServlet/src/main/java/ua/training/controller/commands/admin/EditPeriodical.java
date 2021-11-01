package ua.training.controller.commands.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.controller.commands.Registration;
import ua.training.model.entity.Periodical;
import ua.training.model.service.PeriodicalsService;

import javax.servlet.http.HttpServletRequest;

public class EditPeriodical implements Command {

    private final PeriodicalsService periodicalService;
    private final Logger logger = LogManager.getLogger(Registration.class.getName());

    public EditPeriodical(PeriodicalsService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String periodical_id = request.getParameter("periodical_id");

        if(name == null || name.equals("") && periodical_id != null && !periodical_id.equals("")) {
            logger.info("EditPeriodical form opened");
            Periodical periodical = PeriodicalsService.findById(Integer.parseInt(periodical_id));
            request.setAttribute("periodical_id", periodical_id);
            request.setAttribute("name", periodical.getName());
            request.setAttribute("description", periodical.getDescription());
            request.setAttribute("category", periodical.getCatId());
            request.setAttribute("price", periodical.getPrice());
            return "/WEB-INF/admin/edit_periodical.jsp";
        }

        Periodical periodical = new Periodical();
        periodical.setId(Long.parseLong(periodical_id));
        periodical.setName(name);
        periodical.setDescription(request.getParameter("description"));
        periodical.setCatId(Long.parseLong(request.getParameter("category")));
        periodical.setPrice(Double.parseDouble(request.getParameter("price")));

        logger.info("Periodical editing request : " + "(" + request.getParameterMap().toString() + ")");
        int result = periodicalService.update(periodical);
        if (result < 1) {
            RuntimeException runtimeException = new RuntimeException("User exists");
            request.setAttribute("exception", runtimeException);
            return "/WEB-INF/error.jsp";
        }

        logger.info("Periodical editing (name, description) : "
                + "(" + periodical.getName() + ", " + periodical.getDescription() + ")");

        return "redirect:/app/admin/periodicals";
    }
}
