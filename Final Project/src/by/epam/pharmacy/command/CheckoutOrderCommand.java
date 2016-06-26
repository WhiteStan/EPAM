package by.epam.pharmacy.command;

import by.epam.pharmacy.entity.Order;
import by.epam.pharmacy.logic.CheckoutOrderLogic;
import by.epam.pharmacy.logic.DeleteDrugLogic;
import by.epam.pharmacy.resource.JspParamName;
import by.epam.pharmacy.resource.JspPathName;
import by.epam.pharmacy.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by Lenovo on 18.06.2016.
 */
public class CheckoutOrderCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String[] locale = ((String) request.getSession().getAttribute(JspParamName.PARAM_LOCALE)).split("_");
        request.setAttribute(JspParamName.PARAM_LOCALE, request.getParameter(JspParamName.PARAM_LOCALE));
        Order order = (Order)request.getSession().getAttribute("order");
        CheckoutOrderLogic checkoutOrderLogic = new CheckoutOrderLogic();
        if (!checkoutOrderLogic.checkout(order.getId())) {
            MessageManager messageManager = new MessageManager(new Locale(locale[0], locale[1]));
            request.setAttribute(JspParamName.PARAM_ERROR_DATABASE,
                    messageManager.getProperty("message.errorDataBase"));
        }
        page = JspPathName.PATH_TO_DRUGDELETE_JSP;
        request.getSession().setAttribute(JspParamName.PARAM_PAGE, page);
        return page;
    }

}
