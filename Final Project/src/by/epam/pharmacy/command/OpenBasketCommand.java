package by.epam.pharmacy.command;

import by.epam.pharmacy.entity.Drug;
import by.epam.pharmacy.logic.DrugSelectLogic;
import by.epam.pharmacy.resource.JspParamName;
import by.epam.pharmacy.resource.JspPathName;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Command to open the basket page
 */
public class OpenBasketCommand implements ActionCommand {
    private static final String PARAM_LIST = "drugs";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        request.setAttribute(JspParamName.PARAM_LOCALE, request.getParameter(JspParamName.PARAM_LOCALE));
        ArrayList<Drug> listOfDrugs = new ArrayList<>();
        DrugSelectLogic drugSelectLogic = new DrugSelectLogic();
        HashSet<String> basket = (HashSet<String>) request.getSession().getAttribute("basket");
        if (basket != null) {
            for (String name : basket) {
                listOfDrugs.add(drugSelectLogic.find(name));
            }
        }
        request.getSession().setAttribute(PARAM_LIST, listOfDrugs);
        page = JspPathName.PATH_TO_BASKET_JSP;
        request.getSession().setAttribute(JspParamName.PARAM_PAGE, page);
        return page;
    }
}
