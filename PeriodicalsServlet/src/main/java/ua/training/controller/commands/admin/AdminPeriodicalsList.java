package ua.training.controller.commands.admin;

import ua.training.controller.commands.Command;
import ua.training.model.dao.util.Sorting;
import ua.training.model.dao.util.SortingType;
import ua.training.model.entity.Periodical;
import ua.training.model.service.PeriodicalsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Admin periodicals list command.
 */
public class AdminPeriodicalsList implements Command {

    private final PeriodicalsService periodicalsService;

    private Sorting sortedPeriodicalIdColumn = Sorting.ASC;
    private Sorting sortedNameColumn = Sorting.ASC;
    private Sorting sortedDescriptionColumn = Sorting.ASC;
    private Sorting sortedCategoryColumn = Sorting.ASC;
    private Sorting sortedPriceColumn = Sorting.ASC;
    private Sorting activeSortingWay = Sorting.ASC;
    private SortingType activeSortingType = SortingType.PERIODICAL_ID;

    /**
     * Instantiates a new Admin periodicals list.
     *
     * @param periodicalsService the periodicals service
     */
    public AdminPeriodicalsList(PeriodicalsService periodicalsService) {
        this.periodicalsService = periodicalsService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = 1;
        int recordsOnPage = 10;

        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }

        if (request.getParameter("desc") != null) {
            activeSortingWay = Sorting.DESC;
        } else if (request.getParameter("asc") != null) {
            activeSortingWay = Sorting.ASC;
        }
        else {
            activeSortingWay = Sorting.DEFAULT;
        }

        // reverse sort direction logic
        String sortParam = request.getParameter(activeSortingWay.getType().toLowerCase());
        if (sortParam != null) {
            activeSortingType = SortingType.safeValueOf(sortParam);
            // check for the correct change sort direction
            if (SortingType.PERIODICAL_ID.equals(activeSortingType)) {
                if (sortedPeriodicalIdColumn.equals(activeSortingWay)) {
                    sortedPeriodicalIdColumn = Sorting.reverse(sortedPeriodicalIdColumn);
                }
            } else if (SortingType.NAME.equals(activeSortingType)) {
                if (sortedNameColumn.equals(activeSortingWay)) {
                    sortedNameColumn = Sorting.reverse(sortedNameColumn);
                }
            } else if (SortingType.DESCRIPTION.equals(activeSortingType)) {
                if (sortedDescriptionColumn.equals(activeSortingWay)) {
                    sortedDescriptionColumn = Sorting.reverse(sortedDescriptionColumn);
                }
            } else if (SortingType.CAT_ID.equals(activeSortingType)) {
                if (sortedCategoryColumn.equals(activeSortingWay)) {
                    sortedCategoryColumn = Sorting.reverse(sortedCategoryColumn);
                }
            } else if (SortingType.PRICE.equals(activeSortingType)) {
                if (sortedPriceColumn.equals(activeSortingWay)) {
                    sortedPriceColumn = Sorting.reverse(sortedPriceColumn);
                }
            }
        }

        List<Periodical> periodicals = periodicalsService.getAllPeriodicals((currentPage-1) * recordsOnPage,
                recordsOnPage, activeSortingWay, activeSortingType);
        int periodicalsCount = periodicalsService.getPeriodicalsCount(0);
        int numberOfPages = (int) Math.ceil(periodicalsCount * 1.0 / recordsOnPage);

        request.setAttribute("periodicals", periodicals);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("periodicalsCount", periodicalsCount);

        request.setAttribute("activeSortingWay", activeSortingWay.getType().toLowerCase());
        request.setAttribute("activeSortingType", activeSortingType.getValue());

        request.setAttribute("sortedPeriodicalIdColumn", sortedPeriodicalIdColumn.getType().toLowerCase());
        request.setAttribute("sortedNameColumn", sortedNameColumn.getType().toLowerCase());
        request.setAttribute("sortedDescriptionColumn", sortedDescriptionColumn.getType().toLowerCase());
        request.setAttribute("sortedCategoryColumn", sortedCategoryColumn.getType().toLowerCase());
        request.setAttribute("sortedPriceColumn", sortedPriceColumn.getType().toLowerCase());

        return "/WEB-INF/admin/admin_periodicals.jsp";
    }
}
