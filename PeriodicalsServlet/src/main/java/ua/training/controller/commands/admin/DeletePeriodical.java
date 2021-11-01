package ua.training.controller.commands.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.controller.commands.Registration;
import ua.training.model.service.PeriodicalsService;

import javax.servlet.http.HttpServletRequest;

/**
 * The Delete periodical command.
 */
public class DeletePeriodical implements Command {

    private final PeriodicalsService periodicalService;
    private final Logger logger = LogManager.getLogger(Registration.class.getName());

    /**
     * Instantiates a new Delete periodical command.
     *
     * @param periodicalService the periodical service
     */
    public DeletePeriodical(PeriodicalsService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String periodical_id = request.getParameter("periodical_id");

        if (periodical_id != null && !periodical_id.equals("")) {
            logger.info("Periodical deleting (periodical_id) : " + periodical_id);
            PeriodicalsService.delete(Integer.parseInt(periodical_id));
            return "redirect:/app/admin/periodicals";
        }

        return "/WEB-INF/error.jsp";
    }
}
