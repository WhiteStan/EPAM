package by.epam.pharmacy.logic;

import by.epam.pharmacy.dao.RecipeDAO;
import by.epam.pharmacy.entity.RecipeConfirmation;
import by.epam.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by Lenovo on 11.06.2016.
 */
public class ConfirmRecipesLogic {
    private final static Logger LOG = LogManager.getLogger(RegisterUserLogic.class);

    public boolean confirm(ArrayList<RecipeConfirmation> confirmRecipes, String login) {
        boolean result = false;
        RecipeDAO recipeDAO = new RecipeDAO();
        try {
            recipeDAO.confirmRecipes(confirmRecipes, login);
            result = true;
        } catch (DAOException e) {
            LOG.error(e);
        }
        finally {
            recipeDAO.closeConnection();
        }
        return result;
    }
}
