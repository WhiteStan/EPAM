package by.epam.pharmacy.logic;

import by.epam.pharmacy.dao.RecipeDAO;
import by.epam.pharmacy.entity.DrugOrdered;
import by.epam.pharmacy.entity.Recipe;
import by.epam.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class CheckRecipeLogic {
    private final static Logger LOG = LogManager.getLogger(LoginLogic.class);

    public boolean check(String login, ArrayList<DrugOrdered> drugs) {
        boolean result = true;
        for (DrugOrdered drug : drugs) {
            if (drug.getRecipe() != 0 && validateRecipe(login, drug.getRecipe(), drug.getName())) {
                result = false;
                break;
            }
        }
        return result;
    }

    private boolean validateRecipe(String login, int recipeId, String drugName) {
        boolean result = false;
        RecipeDAO recipeDAO = new RecipeDAO();
        ArrayList<Recipe> userRecipes;
        try {
            userRecipes = recipeDAO.findEntityByName(login);
            if (userRecipes != null) {
                for (Recipe recipe : userRecipes) {
                    if (recipe.getId() == recipeId && recipe.getDrugName() == drugName && recipe.getValid()) {
                        result = true;
                        break;
                    }
                }
            }
        } catch (DAOException e) {
            LOG.error(e);
        } finally {
            recipeDAO.closeConnection();
        }
        return result;
    }
}
