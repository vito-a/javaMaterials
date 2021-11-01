package ua.training.controller.commands.admin;

import ua.training.controller.commands.Command;
import ua.training.model.dao.util.Sorting;
import ua.training.model.dao.util.SortingType;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The Admin Users list command.
 */
public class UsersList implements Command {

    private final UserService userService;

    private Sorting sortedUserIdColumn = Sorting.ASC;
    private Sorting sortedUsernameColumn = Sorting.ASC;
    private Sorting sortedFirstnameColumn = Sorting.ASC;
    private Sorting sortedLastnameColumn = Sorting.ASC;
    private Sorting sortedEmailColumn = Sorting.ASC;
    private Sorting sortedEnabledColumn = Sorting.ASC;
    private Sorting activeSortingWay = Sorting.ASC;
    private SortingType activeSortingType = SortingType.USER_ID;

    /**
     * Instantiates a new Users list.
     *
     * @param userService the user service
     */
    public UsersList(UserService userService) { this.userService = userService; }

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

        // reverse sort direction logic, for now it's only two possible way - sort by name or price
        String sortParam = request.getParameter(activeSortingWay.getType().toLowerCase());
        if (sortParam != null) {
            activeSortingType = SortingType.safeValueOf(sortParam);
            // verification for change the sorting of product name or product price
            if (SortingType.USER_ID.equals(activeSortingType)) {
                // check for the correct change sort direction
                if (sortedUserIdColumn.equals(activeSortingWay)) {
                    sortedUserIdColumn = Sorting.reverse(sortedUserIdColumn);
                }
            } else if (SortingType.USERNAME.equals(activeSortingType)) {
                // check for the correct change sort direction
                if (sortedUsernameColumn.equals(activeSortingWay)) {
                    sortedUsernameColumn = Sorting.reverse(sortedUsernameColumn);
                }
            } else if (SortingType.FIRSTNAME.equals(activeSortingType)) {
                // check for the correct change sort direction
                if (sortedFirstnameColumn.equals(activeSortingWay)) {
                    sortedFirstnameColumn = Sorting.reverse(sortedFirstnameColumn);
                }
            } else if (SortingType.LASTNAME.equals(activeSortingType)) {
                // check for the correct change sort direction
                if (sortedLastnameColumn.equals(activeSortingWay)) {
                    sortedLastnameColumn = Sorting.reverse(sortedLastnameColumn);
                }
            } else if (SortingType.EMAIL.equals(activeSortingType)) {
                // check for the correct change sort direction
                if (sortedEmailColumn.equals(activeSortingWay)) {
                    sortedEmailColumn = Sorting.reverse(sortedEmailColumn);
                }
            } else if (SortingType.ENABLED.equals(activeSortingType)) {
                // check for the correct change sort direction
                if (sortedEnabledColumn.equals(activeSortingWay)) {
                    sortedEnabledColumn = Sorting.reverse(sortedEnabledColumn);
                }
            }
        }

        List<User> users = userService.getAllUsers(0, (currentPage-1) * recordsOnPage,
                recordsOnPage, activeSortingWay, activeSortingType);
        int usersCount = userService.getUsersCount(0);
        int numberOfPages = (int) Math.ceil(usersCount * 1.0 / recordsOnPage);

        request.setAttribute("users", users);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("usersCount", usersCount);

        request.setAttribute("activeSortingWay", activeSortingWay.getType().toLowerCase());
        request.setAttribute("activeSortingType", activeSortingType.getValue());

        request.setAttribute("sortedUserIdColumn", sortedUserIdColumn.getType().toLowerCase());
        request.setAttribute("sortedUsernameColumn", sortedUsernameColumn.getType().toLowerCase());
        request.setAttribute("sortedFirstnameColumn", sortedFirstnameColumn.getType().toLowerCase());
        request.setAttribute("sortedLastnameColumn", sortedLastnameColumn.getType().toLowerCase());
        request.setAttribute("sortedEmailColumn", sortedEmailColumn.getType().toLowerCase());
        request.setAttribute("sortedEnabledColumn", sortedEnabledColumn.getType().toLowerCase());

        return "/WEB-INF/admin/userlist.jsp";
    }
}
