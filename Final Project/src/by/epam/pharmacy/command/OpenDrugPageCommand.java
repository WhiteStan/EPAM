package by.epam.pharmacy.command;

import by.epam.pharmacy.entity.Drug;
import by.epam.pharmacy.logic.DrugSelectLogic;
import by.epam.pharmacy.resource.JspParamName;
import by.epam.pharmacy.resource.JspPathName;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Lenovo on 12.06.2016.
 */
public class OpenDrugPageCommand implements ActionCommand {
    private final static String PARAM_DRUG = "drug";
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String[] locale = ((String) request.getSession().getAttribute(JspParamName.PARAM_LOCALE)).split("_");
        request.setAttribute(JspParamName.PARAM_LOCALE, request.getParameter(JspParamName.PARAM_LOCALE));
        String drugName = request.getParameter("drugName");
        DrugSelectLogic drugSelectLogic = new DrugSelectLogic();
        Drug drug = drugSelectLogic.find(drugName);
        request.getSession().setAttribute(PARAM_DRUG, drug);
        page = JspPathName.PATH_TO_DRUGPAGE_JSP;
        request.getSession().setAttribute(JspParamName.PARAM_PAGE, page);
        return page;
    }
}
