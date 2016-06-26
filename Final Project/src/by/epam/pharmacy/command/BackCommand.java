package by.epam.pharmacy.command;

import by.epam.pharmacy.resource.JspParamName;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Lenovo on 21.06.2016.
 */
public class BackCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = (String) request.getSession().getAttribute("lastPage");
        request.getSession().setAttribute(JspParamName.PARAM_PAGE, page);
        request.setAttribute(JspParamName.PARAM_LOCALE, request.getParameter(JspParamName.PARAM_LOCALE));
        return page;
    }
}
