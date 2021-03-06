package by.epam.pharmacy.logic;

import by.epam.pharmacy.dao.RecipeDAO;
import by.epam.pharmacy.entity.Recipe;
import by.epam.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfirmRecipeRequestLogic {
    private final static Logger LOG = LogManager.getLogger(RegisterUserLogic.class);

    public boolean confirm(Integer id, Recipe recipe) {
        boolean result = false;
        RecipeDAO recipeDAO = new RecipeDAO();
        try {
            recipeDAO.updateRecipe(id, recipe);
            result = true;
        } catch (DAOException e) {
            LOG.error(e);
        } finally {
            recipeDAO.closeConnection();
        }
        return result;
    }
}
