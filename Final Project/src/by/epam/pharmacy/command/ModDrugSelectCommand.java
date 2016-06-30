package by.epam.pharmacy.command;

import by.epam.pharmacy.entity.Drug;
import by.epam.pharmacy.logic.DrugSelectLogic;
import by.epam.pharmacy.resource.JspParamName;
import by.epam.pharmacy.resource.JspPathName;
import by.epam.pharmacy.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Command to select a drug for modification
 */
public class ModDrugSelectCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String[] locale = ((String) request.getSession().getAttribute(JspParamName.PARAM_LOCALE)).split("_");
        request.setAttribute(JspParamName.PARAM_LOCALE, request.getParameter(JspParamName.PARAM_LOCALE));
        String name = request.getParameter(JspParamName.PARAM_NAME);
        DrugSelectLogic drugSelectLogic = new DrugSelectLogic();
        Drug drug = drugSelectLogic.find(name);
        if (drug == null) {
            MessageManager messageManager = new MessageManager(new Locale(locale[0], locale[1]));
            request.setAttribute(JspParamName.PARAM_ERROR_DATABASE,
                    messageManager.getProperty("message.errorDataBase"));
        }
        page = JspPathName.PATH_TO_MODDRUG_JSP;
        request.getSession().setAttribute(JspParamName.PARAM_PAGE, page);
        request.setAttribute("drug", drug);
        return page;
    }
}
