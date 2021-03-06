package by.epam.pharmacy.command;

import by.epam.pharmacy.logic.AddRecipeRequestLogic;
import by.epam.pharmacy.resource.JspParamName;
import by.epam.pharmacy.resource.JspPathName;
import by.epam.pharmacy.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Command to request new recipe by user
 */
public class RequestRecipeCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String[] locale = ((String) request.getSession().getAttribute(JspParamName.PARAM_LOCALE)).split("_");
        request.setAttribute(JspParamName.PARAM_LOCALE, request.getParameter(JspParamName.PARAM_LOCALE));
        String strId;
        Integer id = null;
        strId = request.getParameter("requestRecipe");
        if(strId != null){
            id = Integer.parseInt(strId);
        }
        AddRecipeRequestLogic addRecipeRequestLogic = new AddRecipeRequestLogic();
        if(!addRecipeRequestLogic.add(id)) {
            MessageManager messageManager = new MessageManager(new Locale(locale[0], locale[1]));
            request.setAttribute(JspParamName.PARAM_ERROR_DATABASE,
                    messageManager.getProperty("message.errorDataBase"));
        }
        page = JspPathName.PATH_TO_RECIPEPAGE_JSP;
        request.getSession().setAttribute(JspParamName.PARAM_PAGE, page);
        return page;
    }
}
