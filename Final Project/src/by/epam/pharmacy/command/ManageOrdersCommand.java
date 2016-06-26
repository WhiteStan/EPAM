package by.epam.pharmacy.command;

import by.epam.pharmacy.entity.Order;
import by.epam.pharmacy.logic.RetrieveOrdersLogic;
import by.epam.pharmacy.resource.JspParamName;
import by.epam.pharmacy.resource.JspPathName;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Lenovo on 14.06.2016.
 */
public class ManageOrdersCommand implements ActionCommand {
    private static final String PARAM_LIST = "orders";
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = (String)request.getSession().getAttribute(JspParamName.PARAM_LOGIN);
        String[] locale = ((String) request.getSession().getAttribute(JspParamName.PARAM_LOCALE)).split("_");
        ArrayList<Order> orders;
        RetrieveOrdersLogic retrieveOrdersLogic = new RetrieveOrdersLogic();
        orders = retrieveOrdersLogic.find();
        request.getSession().setAttribute(PARAM_LIST, orders);
        page = JspPathName.PATH_TO_MANAGEORDERS_JSP;
        request.getSession().setAttribute(JspParamName.PARAM_PAGE, page);
        return page;
    }
}
