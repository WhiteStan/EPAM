package by.epam.pharmacy.command;

import by.epam.pharmacy.entity.Recipe;
import by.epam.pharmacy.entity.RecipeConfirmation;
import by.epam.pharmacy.logic.ConfirmRecipesLogic;
import by.epam.pharmacy.resource.JspParamName;
import by.epam.pharmacy.resource.JspPathName;
import by.epam.pharmacy.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Command to validate recipes
 */
public class ConfirmRecipesCommand implements ActionCommand {
    private static final String PARAM_LIST = "patients";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = (String) request.getSession().getAttribute(JspParamName.PARAM_LOGIN);
        String[] locale = ((String) request.getSession().getAttribute(JspParamName.PARAM_LOCALE)).split("_");
        ArrayList<Recipe> recipes = (ArrayList<Recipe>) request.getSession().getAttribute(PARAM_LIST);
        ArrayList<RecipeConfirmation> recipeConfirmations = new ArrayList<>();
        for (Recipe recipe : recipes) {
            String strConfirm = request.getParameter(recipe.getId().toString());
            Boolean confirm = false;
            if (strConfirm != null) {
                confirm = Boolean.parseBoolean(strConfirm);
            }
            RecipeConfirmation recipeConfirmation = new RecipeConfirmation(confirm, recipe.getId());
            recipeConfirmations.add(recipeConfirmation);
        }
        ConfirmRecipesLogic confirmRecipes = new ConfirmRecipesLogic();
        if (!confirmRecipes.confirm(recipeConfirmations, login)) {
            MessageManager messageManager = new MessageManager(new Locale(locale[0], locale[1]));
            request.setAttribute(JspParamName.PARAM_ERROR_DATABASE,
                    messageManager.getProperty("message.errorDataBase"));
        }
        page = JspPathName.PATH_TO_MAIN_JSP;
        request.getSession().setAttribute(JspParamName.PARAM_PAGE, page);
        return page;
    }
}
