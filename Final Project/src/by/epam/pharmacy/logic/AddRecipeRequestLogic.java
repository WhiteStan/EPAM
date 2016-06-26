package by.epam.pharmacy.logic;

import by.epam.pharmacy.dao.RecipeDAO;
import by.epam.pharmacy.entity.Recipe;
import by.epam.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Lenovo on 16.06.2016.
 */
public class AddRecipeRequestLogic {
    private final static Logger LOG = LogManager.getLogger(RegisterUserLogic.class);

    public boolean add(Integer id) {
        boolean result = false;
        RecipeDAO recipeDAO = new RecipeDAO();
        try {
            recipeDAO.addRecipeRequest(id);
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
