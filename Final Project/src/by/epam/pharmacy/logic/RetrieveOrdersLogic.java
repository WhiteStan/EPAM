package by.epam.pharmacy.logic;

import by.epam.pharmacy.dao.OrderDAO;
import by.epam.pharmacy.entity.Order;
import by.epam.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by Lenovo on 14.06.2016.
 */
public class RetrieveOrdersLogic {
    private final static Logger LOG = LogManager.getLogger(LoginLogic.class);

    public ArrayList find() {
        OrderDAO orderDAO = new OrderDAO();
        ArrayList<Order> orders = new ArrayList<>();
        try {
            orders = orderDAO.findAll();
        } catch (DAOException e) {
            LOG.error(e);
        }
        finally {
            orderDAO.closeConnection();
        }
        return orders;
    }
}
