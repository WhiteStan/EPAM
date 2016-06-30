package by.epam.pharmacy.dao;

import by.epam.pharmacy.entity.DrugOrdered;
import by.epam.pharmacy.entity.Order;
import by.epam.pharmacy.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAO extends AbstractDAO {
    private final static String ADD_ORDER = "INSERT INTO `order` (totalPrice, buyerLogin) VALUES(?,?)";
    private final static String ADD_DRUGORDERED = "INSERT INTO `drugsordered` (`drugName`, recipeId, amount, orderId) " +
            "VALUES(?,?,?,?)";
    private final static String FIND_LAST_ID = "SELECT idorder  FROM `order` ORDER BY idorder DESC LIMIT 1 ";
    private final static String GET_ALL_ORDERS = "SELECT totalPrice, buyerLogin, idorder, status," +
            "timeOfDelivery, isValid FROM `order`";
    private final static String CONFIRM_ORDERS = "UPDATE `order` SET status = ?, isValid = ?, timeOfDelivery = ?" +
            " WHERE idorder = ?";
    private final static String GET_ORDER_BY_ID = "SELECT totalPrice, buyerLogin, status,timeOfDelivery, isValid" +
            "  FROM `order` WHERE idorder = ?";
    private final static String GET_DRUGS_ORDERED_BY_ID = "SELECT drugName, amount, recipeId" +
            "  FROM `drugsordered` WHERE orderId = ?";
    private final static String DELETE_ORDER = "DELETE FROM `order` WHERE `idorder` = ?";
    private final static String DELETE_DRUGS_ORDERED = "DELETE FROM drugsordered WHERE `orderid` = ?";

    public boolean addOrder(Order order) throws DAOException {
        PreparedStatement st = null;
        boolean result = false;
        try {
            st = connection.prepareStatement(ADD_ORDER);
            st.setInt(1, order.getTotalPrice());
            st.setString(2, order.getLogin());
            st.executeUpdate();
            result = true;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connection.closeStatement(st);
        }
        return result;
    }

    public boolean confirmOrders(ArrayList<Order> orders) throws DAOException {
        PreparedStatement st = null;
        boolean result = false;
        try {
            st = connection.prepareStatement(CONFIRM_ORDERS);
            for (Order order : orders) {
                st.setString(1, order.getStatus());
                st.setBoolean(2, order.isValid());
                Date sqlTime = null;
                if (order.getTimeOfDelivery() != null) {
                    sqlTime = new Date(order.getTimeOfDelivery().getTime());
                }
                st.setDate(3, sqlTime);
                st.setInt(4, order.getId());
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

    public int findLastId() throws DAOException {
        int lastId = 0;
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(FIND_LAST_ID);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                lastId = resultSet.getInt("idorder");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connection.closeStatement(st);
        }
        return lastId;
    }

    public boolean addDrugOrdered(DrugOrdered drugOrdered, int orderId) throws DAOException {
        PreparedStatement st = null;
        boolean result = false;
        try {
            st = connection.prepareStatement(ADD_DRUGORDERED);
            st.setString(1, drugOrdered.getName());
            if (drugOrdered.getRecipe() != 0) {
                st.setInt(2, drugOrdered.getRecipe());
            } else {
                st.setNull(2, Types.INTEGER);
            }
            st.setInt(3, drugOrdered.getAmount());
            st.setInt(4, orderId);
            st.executeUpdate();
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
        ArrayList<Order> drugs = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(GET_ALL_ORDERS);
            ResultSet resultSet =
                    st.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setLogin(resultSet.getString("buyerLogin"));
                order.setTotalPrice(resultSet.getInt("totalPrice"));
                order.setId(resultSet.getInt("idorder"));
                order.setStatus(resultSet.getString("status"));
                order.setTimeOfDelivery(resultSet.getDate("timeOfDelivery"));
                order.setValid(resultSet.getBoolean("isValid"));
                drugs.add(order);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connection.closeStatement(st);
        }
        return drugs;
    }

    public Order findOrderByOrderId(int orderid) throws DAOException {
        Order order = new Order();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(GET_ORDER_BY_ID);
            st.setInt(1, orderid);
            ResultSet resultSet =
                    st.executeQuery();
            while (resultSet.next()) {
                order.setLogin(resultSet.getString("buyerLogin"));
                order.setTotalPrice(resultSet.getInt("totalPrice"));
                order.setId(orderid);
                order.setStatus(resultSet.getString("status"));
                order.setTimeOfDelivery(resultSet.getDate("timeOfDelivery"));
                order.setValid(resultSet.getBoolean("isValid"));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connection.closeStatement(st);
        }
        return order;
    }

    public ArrayList findDrugOrderedByOrderId(int orderid) throws DAOException
    {
        ArrayList<DrugOrdered> drugOrdereds = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(GET_DRUGS_ORDERED_BY_ID);
            st.setInt(1, orderid);
            ResultSet resultSet =
                    st.executeQuery();
            while (resultSet.next()) {
                DrugOrdered drugOrdered = new DrugOrdered();
                drugOrdered.setName(resultSet.getString("drugName"));
                drugOrdered.setAmount(resultSet.getInt("amount"));
                drugOrdered.setRecipe(resultSet.getInt("recipeId"));
                drugOrdereds.add(drugOrdered);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connection.closeStatement(st);
        }
        return drugOrdereds;
    }

    public boolean deleteOrderById(Integer id) throws DAOException {
        PreparedStatement st = null;
        boolean result = false;
        try {
            st = connection.prepareStatement(DELETE_ORDER);
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

    public boolean deleteDrugsOrderedById(Integer id) throws DAOException {
        PreparedStatement st = null;
        boolean result = false;
        try {
            st = connection.prepareStatement(DELETE_DRUGS_ORDERED);
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
}
