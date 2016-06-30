package by.epam.pharmacy.command;

import by.epam.pharmacy.entity.Recipe;
import by.epam.pharmacy.logic.ConfirmRecipeRequestLogic;
import by.epam.pharmacy.resource.JspParamName;
import by.epam.pharmacy.resource.JspPathName;
import by.epam.pharmacy.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Command to prolong recipes for users
 */
public class ConfirmRecipeRequestCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String[] locale = ((String) request.getSession().getAttribute(JspParamName.PARAM_LOCALE)).split("_");
        request.setAttribute(JspParamName.PARAM_LOCALE, request.getParameter(JspParamName.PARAM_LOCALE));
        String strId;
        Integer id = null;
        Integer amount = null;
        strId = request.getParameter("requestRecipe");
        String strAmount = request.getParameter(strId + "Amount");
        String term = request.getParameter(strId + "Term");
        if (strId != null) {
            id = Integer.parseInt(strId);
        }
        if (strAmount != null) {
            amount = Integer.parseInt(strAmount);
        }
        Recipe recipe = new Recipe();
        recipe.setAmount(amount);
        recipe.setTerm(term);
        ConfirmRecipeRequestLogic confirmRecipeRequestLogic = new ConfirmRecipeRequestLogic();
        if (!confirmRecipeRequestLogic.confirm(id, recipe)) {
            MessageManager messageManager = new MessageManager(new Locale(locale[0], locale[1]));
            request.setAttribute(JspParamName.PARAM_ERROR_DATABASE,
                    messageManager.getProperty("message.errorDataBase"));
        }
        page = JspPathName.PATH_TO_RECIPEPAGE_JSP;
        request.getSession().setAttribute(JspParamName.PARAM_PAGE, page);
        return page;
    }
}
