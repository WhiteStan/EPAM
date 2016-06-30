package by.epam.pharmacy.command;

import by.epam.pharmacy.logic.DeleteDrugLogic;
import by.epam.pharmacy.resource.JspParamName;
import by.epam.pharmacy.resource.JspPathName;
import by.epam.pharmacy.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Command to delete drug
 */
public class DeleteDrugCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String[] locale = ((String) request.getSession().getAttribute(JspParamName.PARAM_LOCALE)).split("_");
        request.setAttribute(JspParamName.PARAM_LOCALE, request.getParameter(JspParamName.PARAM_LOCALE));
        String name = request.getParameter(JspParamName.PARAM_NAME);
        DeleteDrugLogic deleteDrugLogic = new DeleteDrugLogic();
        if (!deleteDrugLogic.delete(name)) {
            MessageManager messageManager = new MessageManager(new Locale(locale[0], locale[1]));
            request.setAttribute(JspParamName.PARAM_ERROR_DATABASE,
                    messageManager.getProperty("message.errorDataBase"));
        }
        page = JspPathName.PATH_TO_DRUGDELETE_JSP;
        request.getSession().setAttribute(JspParamName.PARAM_PAGE, page);
        return page;
    }
}
