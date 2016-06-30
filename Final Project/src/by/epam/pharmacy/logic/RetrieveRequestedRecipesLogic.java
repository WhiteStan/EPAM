package by.epam.pharmacy.logic;

import by.epam.pharmacy.dao.RecipeDAO;
import by.epam.pharmacy.entity.Recipe;
import by.epam.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class RetrieveRequestedRecipesLogic {
    private final static Logger LOG = LogManager.getLogger(RegisterUserLogic.class);

    public ArrayList find() {
        ArrayList<Recipe> recipes = new ArrayList<>();
        RecipeDAO recipeDAO = new RecipeDAO();
        try {
            recipes = recipeDAO.findAllRequestedRecipes();
        } catch (DAOException e) {
            LOG.error(e);
        } finally {
            recipeDAO.closeConnection();
        }
        return recipes;
    }
}
