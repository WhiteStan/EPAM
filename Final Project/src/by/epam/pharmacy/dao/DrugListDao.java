package by.epam.pharmacy.dao;

import by.epam.pharmacy.entity.Drug;
import by.epam.pharmacy.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for work with `drugs` table
 */
public class DrugListDao extends AbstractDAO {
    private final static String GET_ALL_DRUGS = "SELECT iddrugs, `name`, internationalName, price," +
            "amount, measurementUnit, inStock, recipeNeed, description FROM drugs";
    private final static String ADD_IN_STOCK = "UPDATE drugs SET inStock = inStock + ? WHERE `name` = ?";
    private final static String ADD_DRUG = "INSERT INTO drugs (`name`, `internationalName`,`price`," +
            "`amount`, `measurementUnit`, `inStock`, `recipeNeed`, `description`) VALUES(?,?,?,?,?,?,?,?)";
    private final static String DELETE_DRUG = "DELETE FROM drugs WHERE `name` = ?";
    private final static String GET_DRUG_BY_NAME = "SELECT iddrugs, `name`, internationalName, price," +
            "amount, measurementUnit, inStock, recipeNeed, description FROM drugs WHERE `name`=?";
    private final static String UPDATE_DRUG = "UPDATE drugs SET `name` =?, internationalName=?, price=?," +
            "amount=?, measurementUnit=?, inStock=?, recipeNeed=?, description=? WHERE `name`=?";
    private final static String SUBSTRACT_IN_STOCK = "UPDATE drugs SET inStock = inStock - ? WHERE `name` = ?";
    private final static String GET_PRICE_BY_NAME = "SELECT price FROM drugs WHERE `name` = ?";
    private final static String GET_TEN_DRUGS = "SELECT iddrugs, `name`, internationalName, price," +
            "amount, measurementUnit, inStock, recipeNeed, description FROM `drugs` LIMIT ?,?";

    @Override
    public List<Drug> findAll() throws DAOException {
        ArrayList<Drug> drugs = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(GET_ALL_DRUGS);
            ResultSet resultSet =
                    st.executeQuery();
            while (resultSet.next()) {
                Drug drug = new Drug();
                drug.setIdDrugs(resultSet.getInt("iddrugs"));
                drug.setName(resultSet.getString("name"));
                drug.setInternationalName(resultSet.getString("internationalName"));
                drug.setPrice(resultSet.getInt("price"));
                drug.setAmount(resultSet.getInt("amount"));
                drug.setMeasurementUnit(resultSet.getString("measurementUnit"));
                drug.setInStock(resultSet.getInt("inStock"));
                drug.setRecipeNeed(resultSet.getBoolean("recipeNeed"));
                drug.setDescription(resultSet.getString("description"));
                drugs.add(drug);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connection.closeStatement(st);
        }
        return drugs;
    }

    public List<Drug> findTenDrugs(int startRow, int lastRow) throws DAOException {
        ArrayList<Drug> drugs = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(GET_TEN_DRUGS);
            st.setInt(1, startRow);
            st.setInt(2, lastRow);
            ResultSet resultSet =
                    st.executeQuery();
            while (resultSet.next()) {
                Drug drug = new Drug();
                drug.setIdDrugs(resultSet.getInt("iddrugs"));
                drug.setName(resultSet.getString("name"));
                drug.setInternationalName(resultSet.getString("internationalName"));
                drug.setPrice(resultSet.getInt("price"));
                drug.setAmount(resultSet.getInt("amount"));
                drug.setMeasurementUnit(resultSet.getString("measurementUnit"));
                drug.setInStock(resultSet.getInt("inStock"));
                drug.setRecipeNeed(resultSet.getBoolean("recipeNeed"));
                drug.setDescription(resultSet.getString("description"));
                drugs.add(drug);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connection.closeStatement(st);
        }
        return drugs;
    }

    public boolean deleteDrug(String name) throws DAOException {
        PreparedStatement st = null;
        boolean result = false;
        try {
            st = connection.prepareStatement(DELETE_DRUG);
            st.setString(1, name);
            int checkUpdate = st.executeUpdate();
            if (checkUpdate != 0) {
                result = true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connection.closeStatement(st);
        }
        return result;
    }

    public boolean addDrug(Drug drug) throws DAOException {
        PreparedStatement st = null;
        boolean result = false;
        try {
            st = connection.prepareStatement(ADD_DRUG);
            st.setString(1, drug.getName());
            st.setString(2, drug.getInternationalName());
            st.setInt(3, drug.getPrice());
            st.setInt(4, drug.getAmount());
            st.setString(5, drug.getMeasurementUnit());
            st.setInt(6, drug.getInStock());
            st.setBoolean(7, drug.isRecipeNeed());
            st.setString(8, drug.getDescription());
            st.executeUpdate();
            result = true;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connection.closeStatement(st);
        }
        return result;
    }

    public boolean addProduct(String drugName, int amount) throws DAOException {
        PreparedStatement st = null;
        boolean result = false;
        try {
            st = connection.prepareStatement(ADD_IN_STOCK);
            st.setInt(1, amount);
            st.setString(2, drugName);
            int updatedRows = st.executeUpdate();
            if (updatedRows != 0) {
                result = true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connection.closeStatement(st);
        }
        return result;
    }

    public int getPriceByName(String name) throws DAOException {
        int price = 0;
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(GET_PRICE_BY_NAME);
            st.setString(1, name);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                price = resultSet.getInt("price");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connection.closeStatement(st);
        }
        return price;
    }

    public boolean updateDrug(Drug drug) throws DAOException {
        PreparedStatement st = null;
        boolean result = false;
        try {
            st = connection.prepareStatement(UPDATE_DRUG);
            st.setString(1, drug.getName());
            st.setString(2, drug.getInternationalName());
            st.setInt(3, drug.getPrice());
            st.setInt(4, drug.getAmount());
            st.setString(5, drug.getMeasurementUnit());
            st.setInt(6, drug.getInStock());
            st.setBoolean(7, drug.isRecipeNeed());
            st.setString(8, drug.getDescription());
            st.setString(9, drug.getName());
            st.executeUpdate();
            result = true;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connection.closeStatement(st);
        }
        return result;
    }

    public Drug findDrug(String name) throws DAOException {
        Drug drug = null;
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(GET_DRUG_BY_NAME);
            st.setString(1, name);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                drug = new Drug();
                drug.setIdDrugs(resultSet.getInt("iddrugs"));
                drug.setName(resultSet.getString("name"));
                drug.setInternationalName(resultSet.getString("internationalName"));
                drug.setPrice(resultSet.getInt("price"));
                drug.setAmount(resultSet.getInt("amount"));
                drug.setMeasurementUnit(resultSet.getString("measurementUnit"));
                drug.setInStock(resultSet.getInt("inStock"));
                drug.setRecipeNeed(resultSet.getBoolean("recipeNeed"));
                drug.setDescription(resultSet.getString("description"));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connection.closeStatement(st);
        }
        return drug;
    }

    public boolean reduceInStock(String name, int amount) throws DAOException {
        PreparedStatement st = null;
        boolean result = false;
        try {
            st = connection.prepareStatement(SUBSTRACT_IN_STOCK);
            st.setInt(1, amount);
            st.setString(2, name);
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
