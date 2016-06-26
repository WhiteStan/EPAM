package by.epam.pharmacy.command;

import by.epam.pharmacy.entity.Drug;
import by.epam.pharmacy.logic.DrugSelectLogic;
import by.epam.pharmacy.resource.JspParamName;
import by.epam.pharmacy.resource.JspPathName;
import by.epam.pharmacy.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by Lenovo on 14.06.2016.
 */
public class SearchDruggistCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String[] locale = ((String) request.getSession().getAttribute(JspParamName.PARAM_LOCALE)).split("_");
        request.setAttribute(JspParamName.PARAM_LOCALE, request.getParameter(JspParamName.PARAM_LOCALE));
        String drugName = request.getParameter(JspParamName.PARAM_DRUGNAME);
        DrugSelectLogic drugSelectLogic = new DrugSelectLogic();
        Drug drug = drugSelectLogic.find(drugName);
        page = JspPathName.PATH_TO_DRUGPAGE_JSP;
        request.getSession().setAttribute(JspParamName.PARAM_PAGE, page);
        request.setAttribute("drug", drug);
        return page;
    }
}
