package by.epam.pharmacy.command;

import by.epam.pharmacy.entity.Drug;
import by.epam.pharmacy.logic.AddDrugLogic;
import by.epam.pharmacy.logic.AddProductLogic;
import by.epam.pharmacy.resource.JspParamName;
import by.epam.pharmacy.resource.JspPathName;
import by.epam.pharmacy.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by Lenovo on 20.06.2016.
 */
public class AddProductCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String name = request.getParameter(JspParamName.PARAM_NAME);
        String strAmount = request.getParameter(JspParamName.PARAM_AMOUNT);
        Integer amount = null;
        if (strAmount != null) {
            amount = Integer.parseInt(strAmount);
        }
        String[] locale = ((String) request.getSession().getAttribute(JspParamName.PARAM_LOCALE)).split("_");
        AddProductLogic addProductLogic = new AddProductLogic();
        if(!addProductLogic.add(name, amount)){
            MessageManager messageManager = new MessageManager(new Locale(locale[0], locale[1]));
            request.setAttribute(JspParamName.PARAM_ERROR_DATABASE,
                    messageManager.getProperty("message.errorDataBase"));
        }
        page = JspPathName.PATH_TO_ADD_PRODUCT_JSP;
        request.getSession().setAttribute(JspParamName.PARAM_PAGE, page);
        return page;
    }
}
