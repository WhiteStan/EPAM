package by.epam.pharmacy.logic;

import by.epam.pharmacy.dao.LoginInfoDAO;
import by.epam.pharmacy.dao.RecipeDAO;
import by.epam.pharmacy.entity.DrugOrdered;
import by.epam.pharmacy.entity.LoginInfo;
import by.epam.pharmacy.entity.Recipe;
import by.epam.pharmacy.exception.DAOException;
import com.mysql.fabric.xmlrpc.base.Array;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by Lenovo on 22.06.2016.
 */
public class CheckRecipeLogic {
    private final static Logger LOG = LogManager.getLogger(LoginLogic.class);

    public boolean check(String login, ArrayList<DrugOrdered> drugs) {
        boolean result = true;
        for(DrugOrdered drug : drugs){
            if(!(drug.getRecipe() != 0 && validateRecipe(login, drug.getRecipe()))){
                result = false;
                break;
            }
        }
        return result;
    }

    private boolean validateRecipe(String login, int recipeId){
        boolean result = false;
        RecipeDAO recipeDAO = new RecipeDAO();
        ArrayList<Recipe> userRecipes;
        try {
                userRecipes = recipeDAO.findEntityByName(login);
                for (Recipe recipe : userRecipes) {
                    if (recipe.getId() == recipeId && recipe.getValid()) {
                        result = true;
                        break;
                    }
                }
        } catch (DAOException e) {
            LOG.error(e);
        }
        finally {
            recipeDAO.closeConnection();
        }
        return result;
    }
}
