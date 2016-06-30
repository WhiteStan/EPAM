package by.epam.pharmacy.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Defines interface for all commands
 */
public interface ActionCommand {

    /**
     * Execute specified command.
     * @param request the {@link HttpServletRequest} object
     * @return PagePath to next page/
     */
    String execute(HttpServletRequest request);
}