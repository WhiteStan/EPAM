package by.epam.pharmacy.command;

import by.epam.pharmacy.resource.JspParamName;

import javax.servlet.http.HttpServletRequest;

/**
 * Command to open page specified in a parameter
 */
public class OpenPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = request.getParameter(JspParamName.PARAM_PAGE);
        request.getSession().setAttribute(JspParamName.PARAM_PAGE, page);
        request.setAttribute(JspParamName.PARAM_LOCALE, request.getParameter(JspParamName.PARAM_LOCALE));
        return page;
    }
}
