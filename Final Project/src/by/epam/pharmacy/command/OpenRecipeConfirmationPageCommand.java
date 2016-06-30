package by.epam.pharmacy.command;

import by.epam.pharmacy.entity.Recipe;
import by.epam.pharmacy.logic.RetrieveRequestedRecipesLogic;
import by.epam.pharmacy.resource.JspParamName;
import by.epam.pharmacy.resource.JspPathName;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Command to open page to prolong user recipes
 */
public class OpenRecipeConfirmationPageCommand implements ActionCommand{
    private final static String PARAM_LIST = "lst";
    @Override
    public String execute(HttpServletRequest request) {
        String page;
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
