package by.epam.pharmacy.logic;

import by.epam.pharmacy.dao.OrderDAO;
import by.epam.pharmacy.dao.RecipeDAO;
import by.epam.pharmacy.entity.Order;
import by.epam.pharmacy.entity.Recipe;
import by.epam.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by Lenovo on 17.06.2016.
 */
public class ConfirmOrdersLogic {
    private final static Logger LOG = LogManager.getLogger(RegisterUserLogic.class);

    public boolean confirm(ArrayList<Order> orderers) {
        boolean result = false;
        OrderDAO orderDAO = new OrderDAO();
        try {
            orderDAO.confirmOrders(orderers);
            result = true;
        } catch (DAOException e) {
            LOG.error(e);
        }
        finally {
            orderDAO.closeConnection();
        }
        return result;
    }
}
