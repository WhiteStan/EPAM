package by.epam.pharmacy.command;

import by.epam.pharmacy.entity.Order;
import by.epam.pharmacy.logic.ConfirmOrdersLogic;
import by.epam.pharmacy.logic.RegisterUserLogic;
import by.epam.pharmacy.resource.JspParamName;
import by.epam.pharmacy.resource.JspPathName;
import by.epam.pharmacy.resource.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Command to confirm received orders
 */
public class ConfirmOrdersCommand implements ActionCommand {
    private final static Logger LOG = LogManager.getLogger(RegisterUserLogic.class);

    private static final String PARAM_LIST = "orders";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = (String) request.getSession().getAttribute(JspParamName.PARAM_LOGIN);
        String[] locale = ((String) request.getSession().getAttribute(JspParamName.PARAM_LOCALE)).split("_");
        ArrayList<Order> orders = (ArrayList<Order>) request.getSession().getAttribute(PARAM_LIST);
        try {
            for (Order order : orders) {
                String timeOfDelivery = request.getParameter(order.getId() + "TimeOfDelivery");
                String status = request.getParameter(order.getId() + "Status");
                String strConfirm = request.getParameter(order.getId().toString());
                Boolean confirm = false;
                if (strConfirm != null) {
                    confirm = Boolean.parseBoolean(strConfirm);
                }
                order.setValid(confirm);
                order.setStatus(status);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date dateTime = formatter.parse(timeOfDelivery);
                order.setTimeOfDelivery(dateTime);
                order.setId(order.getId());
            }
        } catch (ParseException e) {
            LOG.error(e);
        }
        ConfirmOrdersLogic confirmOrdersLogic = new ConfirmOrdersLogic();
        if (!confirmOrdersLogic.confirm(orders)) {
            MessageManager messageManager = new MessageManager(new Locale(locale[0], locale[1]));
            request.setAttribute(JspParamName.PARAM_ERROR_DATABASE,
                    messageManager.getProperty("message.errorDataBase"));
        }
        page = JspPathName.PATH_TO_MANAGEORDERS_JSP;
        request.getSession().setAttribute(JspParamName.PARAM_PAGE, page);
        return page;
    }
}
