package by.epam.pharmacy.command;

import by.epam.pharmacy.entity.Drug;
import by.epam.pharmacy.logic.ListTenDrugsLogic;
import by.epam.pharmacy.resource.JspParamName;
import by.epam.pharmacy.resource.JspPathName;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Lenovo on 05.05.2016.
 */
public class DrugListCommand implements ActionCommand {
    private static final String PARAM_LIST = "drugList";
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String[] locale = ((String) request.getSession().getAttribute(JspParamName.PARAM_LOCALE)).split("_");
        request.setAttribute(JspParamName.PARAM_LOCALE, request.getParameter(JspParamName.PARAM_LOCALE));
        String strPageNumber = request.getParameter("pageNum");
        Integer pageNumber = 0;
        if(pageNumber!= null)
        {
            pageNumber = Integer.parseInt(strPageNumber);
        }
        ArrayList<Drug> listOfDrugs;
        ListTenDrugsLogic listTenDrugsLogic = new ListTenDrugsLogic();
        listOfDrugs = listTenDrugsLogic.find(pageNumber);
        request.getSession().setAttribute(PARAM_LIST, listOfDrugs);
        page = JspPathName.PATH_TO_DRUGLIST_JSP;
        request.getSession().setAttribute(JspParamName.PARAM_PAGE, JspPathName.PATH_TO_DRUGLIST_JSP);
        return page;
    }
}
