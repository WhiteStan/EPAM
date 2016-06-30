package by.epam.pharmacy.command;

import by.epam.pharmacy.entity.Drug;
import by.epam.pharmacy.logic.DrugSelectLogic;
import by.epam.pharmacy.resource.JspParamName;
import by.epam.pharmacy.resource.JspPathName;

import javax.servlet.http.HttpServletRequest;

/**
 * Command to search for a drug
 */
public class SearchDruggistCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        request.setAttribute(JspParamName.PARAM_LOCALE, request.getParameter(JspParamName.PARAM_LOCALE));
        String drugName = request.getParameter(JspParamName.PARAM_DRUGNAME);
        DrugSelectLogic drugSelectLogic = new DrugSelectLogic();
        Drug drug = drugSelectLogic.find(drugName);
        page = JspPathName.PATH_TO_DRUGPAGE_JSP;
        request.getSession().setAttribute(JspParamName.PARAM_PAGE, page);
        request.getSession().setAttribute("drug", drug);
        return page;
    }
}
