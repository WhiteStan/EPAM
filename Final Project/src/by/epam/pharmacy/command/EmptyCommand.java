package by.epam.pharmacy.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.pharmacy.resource.JspPathName;

/**
 * Command used in case of wrong command
 */
public class EmptyCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = JspPathName.PATH_TO_MAIN_JSP;
        return page;
    }
}