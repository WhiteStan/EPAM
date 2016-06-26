package by.epam.pharmacy.command;

import by.epam.pharmacy.entity.Drug;
import by.epam.pharmacy.entity.Recipe;
import by.epam.pharmacy.logic.DrugSelectLogic;
import by.epam.pharmacy.logic.SelectRecipesLogic;
import by.epam.pharmacy.resource.JspParamName;
import by.epam.pharmacy.resource.JspPathName;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Lenovo on 15.06.2016.
 */
public class OpenRecipePageCommand implements ActionCommand {
    private final static String PARAM_LIST = "lst";
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String[] locale = ((String) request.getSession().getAttribute(JspParamName.PARAM_LOCALE)).split("_");
        request.setAttribute(JspParamName.PARAM_LOCALE, request.getParameter(JspParamName.PARAM_LOCALE));
        String login = (String)request.getSession().getAttribute("login");
        SelectRecipesLogic selectRecipesLogic = new SelectRecipesLogic();
        ArrayList<Recipe> recipes;
        recipes = selectRecipesLogic.find(login);
        request.getSession().setAttribute(PARAM_LIST, recipes);
        page = JspPathName.PATH_TO_RECIPEPAGE_JSP;
        request.getSession().setAttribute(JspParamName.PARAM_PAGE, page);
        return page;
    }
}
