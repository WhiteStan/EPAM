package by.epam.pharmacy.logic;

import by.epam.pharmacy.dao.DrugListDao;
import by.epam.pharmacy.dao.RecipeDAO;
import by.epam.pharmacy.entity.Recipe;
import by.epam.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by Lenovo on 15.06.2016.
 */
public class SelectRecipesLogic {
    private final static Logger LOG = LogManager.getLogger(LoginLogic.class);

    public ArrayList find(String name) {
        ArrayList<Recipe> recipes = null;
        RecipeDAO recipeDAO = new RecipeDAO();
        try {
            recipes = recipeDAO.findEntityByName(name);
        } catch (DAOException e) {
            LOG.error(e);
        }
        finally {
            recipeDAO.closeConnection();
        }
        return recipes;
    }
}
