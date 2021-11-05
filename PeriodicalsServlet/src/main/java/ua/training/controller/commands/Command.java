package ua.training.controller.commands;

import javax.servlet.http.HttpServletRequest;

/**
 * The general Command interface.
 */
public interface Command {
    /**
     * The generic Execute method for commands.
     *
     * @param request the request
     * @return the string
     */
    String execute(HttpServletRequest request);
}
