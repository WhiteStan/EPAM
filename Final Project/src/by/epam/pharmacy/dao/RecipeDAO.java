package by.epam.pharmacy.dao;

import by.epam.pharmacy.entity.Recipe;
import by.epam.pharmacy.entity.RecipeConfirmation;
import by.epam.pharmacy.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Lenovo on 11.06.2016.
 */
public class RecipeDAO extends AbstractDAO{
    private final static String ADD_RECIPE = "INSERT INTO recipe (drugName, patientLogin, amountOfDrug, doctorLogin, measurementUnit)" +
            " VALUES(?,?,?,?,?)";
    private final static String ADD_RECIPE_REQUEST = "UPDATE recipe SET isRequsted = 1  WHERE idrecipe = ?";
    private final static String GET_ALL_REQUSTED_RECIPES = "SELECT idrecipe, patientLogin, amountOfDrug, drugName  FROM recipe WHERE isRequsted = 1";
    private final static String GET_ALL_RECIPES = "SELECT idrecipe, patientLogin, amountOfDrug, drugName, isValid  FROM recipe";
    private final static String CONFIRM_RECIPES = "UPDATE recipe SET doctorLogin = ?, isValid = ? WHERE idrecipe = ?";
    private final static String GET_RECIPE_BY_NAME = "SELECT drugName, amountOfDrug, term, idrecipe FROM recipe " +
            "WHERE patientLogin=?";
    private final static String UPDATE_RECIPE = "UPDATE recipe SET `amountOfDrug` =?, term=?, isRequsted = 0  WHERE idrecipe =?";

    public boolean addRecipe(Recipe recipe) throws DAOException{
        PreparedStatement st = null;
        boolean result = false;
        try {
            st = connection.prepareStatement(ADD_RECIPE);
            st.setString(1, recipe.getDrugName());
            st.setString(2, recipe.getLogin());
            st.setInt(3, recipe.getAmount());
            st.setString(4, recipe.getDoctorLogin());
            st.setString(5, recipe.getMeasurementUnit());
            st.executeUpdate();
            result = true;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connection.closeStatement(st);
        }
        return result;
    }

    public boolean addRecipeRequest(Integer id) throws DAOException{
        PreparedStatement st = null;
        boolean result = false;
        try {
            st = connection.prepareStatement(ADD_RECIPE_REQUEST);
            st.setInt(1, id);
            st.executeUpdate();
            result = true;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connection.closeStatement(st);
        }
        return result;
    }

    public boolean confirmRecipes(ArrayList<RecipeConfirmation> recipeConfirmations, String login) throws DAOException {
        PreparedStatement st = null;
        boolean result = false;
        try {
            st = connection.prepareStatement(CONFIRM_RECIPES);
            for(RecipeConfirmation recipeConfirmation: recipeConfirmations){
                st.setString(1, login);
                st.setBoolean(2, recipeConfirmation.isConfirmation());
                st.setInt(3, recipeConfirmation.getId());
                st.executeUpdate();
            }
            result = true;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connection.closeStatement(st);
        }
        return result;
    }

    @Override
    public ArrayList findAll() throws DAOException {
        ArrayList<Recipe> recipes = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(GET_ALL_RECIPES);
            ResultSet resultSet =
                    st.executeQuery();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setAmount(resultSet.getInt("amountOfDrug"));
                recipe.setId(resultSet.getInt("idrecipe"));
                recipe.setLogin(resultSet.getString("patientLogin"));
                recipe.setDrugName(resultSet.getString("DrugName"));
                recipe.setValid(resultSet.getBoolean("isValid"));
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connection.closeStatement(st);
        }
        return recipes;
    }

    public ArrayList findAllRequestedRecipes() throws DAOException {
        ArrayList<Recipe> recipes = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(GET_ALL_REQUSTED_RECIPES);
            ResultSet resultSet =
                    st.executeQuery();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setAmount(resultSet.getInt("amountOfDrug"));
                recipe.setId(resultSet.getInt("idrecipe"));
                recipe.setLogin(resultSet.getString("patientLogin"));
                recipe.setDrugName(resultSet.getString("DrugName"));
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connection.closeStatement(st);
        }
        return recipes;
    }

    public ArrayList findEntityByName(String login) throws DAOException {
        PreparedStatement st = null;
        ArrayList<Recipe> recipes = new ArrayList<>();
        try {
            st = connection.prepareStatement(GET_RECIPE_BY_NAME);
            st.setString(1, login);
            ResultSet resultSet = st.executeQuery();
            while(resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setDrugName(resultSet.getString("drugName"));
                recipe.setTerm(resultSet.getString("term"));
                recipe.setAmount(resultSet.getInt("amountOfDrug"));
                recipe.setId(resultSet.getInt("idrecipe"));
                recipes.add(recipe);
            }
        }  catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connection.closeStatement(st);
        }
        return recipes;
    }

    public boolean updateRecipe(Integer id, Recipe recipe) throws DAOException {
        PreparedStatement st = null;
        boolean result = false;
        try {
            st = connection.prepareStatement(UPDATE_RECIPE);
            st.setInt(1, recipe.getAmount());
            st.setString(2, recipe.getTerm());
            st.setInt(3, id);
            st.executeUpdate();
            result = true;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connection.closeStatement(st);
        }
        return result;
    }
}
