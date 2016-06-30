package by.epam.pharmacy.command;

import by.epam.pharmacy.entity.DrugOrdered;
import by.epam.pharmacy.entity.Order;
import by.epam.pharmacy.logic.SearchDrugsOrderedLogic;
import by.epam.pharmacy.logic.SearchOrderLogic;
import by.epam.pharmacy.resource.JspParamName;
import by.epam.pharmacy.resource.JspPathName;
import by.epam.pharmacy.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Command to search for an order
 */
public class SearchOrderCommand implements ActionCommand {
    private final static String PARAM_ORDER_ID = "orderId";
    private final static String PARAM_DRUGS_ORDERED_LIST = "drugsOrdered";
    private final static String PARAM_ORDER = "order";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String[] locale = ((String) request.getSession().getAttribute(JspParamName.PARAM_LOCALE)).split("_");
        request.setAttribute(JspParamName.PARAM_LOCALE, request.getParameter(JspParamName.PARAM_LOCALE));
        String strOrderId = request.getParameter(PARAM_ORDER_ID);
        Integer orderId = null;
        if(strOrderId != null){
            orderId = Integer.parseInt(strOrderId);
        }
        SearchOrderLogic searchOrderLogic = new SearchOrderLogic();
        Order order = searchOrderLogic.find(orderId);
        SearchDrugsOrderedLogic searchDrugsOrderedLogic = new SearchDrugsOrderedLogic();
        ArrayList<DrugOrdered> drugOrdereds = searchDrugsOrderedLogic.search(orderId);
        if (order == null) {
            MessageManager messageManager = new MessageManager(new Locale(locale[0], locale[1]));
            request.setAttribute(JspParamName.PARAM_ERROR_LOGIN,
                    messageManager.getProperty("message.loginerror"));
        }
        page = JspPathName.PATH_TO_CHECKOUT_ORDER_JSP;
        request.getSession().setAttribute(JspParamName.PARAM_PAGE, page);
        request.getSession().setAttribute(PARAM_DRUGS_ORDERED_LIST, drugOrdereds);
        request.getSession().setAttribute(PARAM_ORDER, order);
        return page;
    }
}
