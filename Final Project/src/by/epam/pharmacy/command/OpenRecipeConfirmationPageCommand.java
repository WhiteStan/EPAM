package by.epam.pharmacy.command;

import by.epam.pharmacy.entity.Recipe;
import by.epam.pharmacy.logic.RetrieveRecipesLogic;
import by.epam.pharmacy.logic.RetrieveRequestedRecipesLogic;
import by.epam.pharmacy.logic.SelectRecipesLogic;
import by.epam.pharmacy.resource.JspParamName;
import by.epam.pharmacy.resource.JspPathName;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Lenovo on 16.06.2016.
 */
public class OpenRecipeConfirmationPageCommand implements ActionCommand{
    private final static String PARAM_LIST = "lst";
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String[] locale = ((String) request.getSession().getAttribute(JspParamName.PARAM_LOCALE)).split("_");
        request.setAttribute(JspParamName.PARAM_LOCALE, request.getParameter(JspParamName.PARAM_LOCALE));
        RetrieveRequestedRecipesLogic retrieveRequestedRecipesLogic = new RetrieveRequestedRecipesLogic();
        ArrayList<Recipe> recipes;
        recipes = retrieveRequestedRecipesLogic.find();
        request.getSession().setAttribute(PARAM_LIST, recipes);
        page = JspPathName.PATH_TO_RECIPE_CONFIRMATION_PAGE_JSP;
        request.getSession().setAttribute(JspParamName.PARAM_PAGE, page);
        return page;
    }
}
