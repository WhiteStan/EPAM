package by.epam.pharmacy.command;

import by.epam.pharmacy.entity.Drug;
import by.epam.pharmacy.logic.DrugListLogic;
import by.epam.pharmacy.resource.JspParamName;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Command to add some drug to user basket
 */
public class AddToBasketCommand implements ActionCommand {
    private static final String PARAM_LIST = "lst";

    @Override
    public String execute(HttpServletRequest request) {
        String page = (String) request.getSession().getAttribute(JspParamName.PARAM_PAGE);
        request.setAttribute(JspParamName.PARAM_LOCALE, request.getParameter(JspParamName.PARAM_LOCALE));
        HashSet<String> basket;
        basket = (HashSet<String>) request.getSession().getAttribute("basket");
        String name = request.getParameter("drugToBasket");
        if (basket == null) {
            basket = new HashSet<>();
        }
        basket.add(name);
        request.getSession().setAttribute("basket", basket);
        ArrayList<Drug> listOfDrugs;
        DrugListLogic drugListLogic = new DrugListLogic();
        listOfDrugs = drugListLogic.listDrugs();
        request.getSession().setAttribute(PARAM_LIST, listOfDrugs);
        request.getSession().setAttribute(JspParamName.PARAM_PAGE, page);
        return page;
    }
}
