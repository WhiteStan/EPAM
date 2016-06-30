package by.epam.pharmacy.command;

import by.epam.pharmacy.entity.Drug;
import by.epam.pharmacy.logic.DrugSelectLogic;
import by.epam.pharmacy.resource.JspParamName;
import by.epam.pharmacy.resource.JspPathName;

import javax.servlet.http.HttpServletRequest;

/**
 * Open a page with description of drug
 */
public class OpenDrugPageCommand implements ActionCommand {
    private final static String PARAM_DRUG = "drug";
    @Override
    public String execute(HttpServletRequest request) {
        String page;
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
