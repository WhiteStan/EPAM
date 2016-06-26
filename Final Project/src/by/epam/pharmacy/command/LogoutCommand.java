package by.epam.pharmacy.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.pharmacy.resource.JspParamName;
import by.epam.pharmacy.resource.JspPathName;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = JspPathName.PATH_TO_INDEX_JSP;
        request.getSession().setAttribute(JspParamName.PARAM_PAGE, page);
        String locale = (String) request.getSession().getAttribute(JspParamName.PARAM_LOCALE);
        request.getSession().invalidate();
        request.getSession().setAttribute(JspParamName.PARAM_LOCALE, locale);
        return page;
    }
}