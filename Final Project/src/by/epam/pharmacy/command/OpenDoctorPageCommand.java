package by.epam.pharmacy.command;

import by.epam.pharmacy.entity.Recipe;
import by.epam.pharmacy.logic.RetrieveRecipesLogic;
import by.epam.pharmacy.resource.JspParamName;
import by.epam.pharmacy.resource.JspPathName;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Command to open the page for recipes modification
 */
public class OpenDoctorPageCommand implements ActionCommand {
    private static final String PARAM_LIST = "patients";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        request.setAttribute(JspParamName.PARAM_LOCALE, request.getParameter(JspParamName.PARAM_LOCALE));

        ArrayList<Recipe> listOfRecipes;
        RetrieveRecipesLogic retrieveRecipesLogic = new RetrieveRecipesLogic();
        listOfRecipes = retrieveRecipesLogic.find();
        request.getSession().setAttribute(PARAM_LIST, listOfRecipes);
        page = JspPathName.PATH_TO_MODRECIPE_JSP;
        request.getSession().setAttribute(JspParamName.PARAM_PAGE, page);
        return page;
    }
}
