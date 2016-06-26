package by.epam.pharmacy.logic;

import by.epam.pharmacy.dao.DrugListDao;
import by.epam.pharmacy.dao.OrderDAO;
import by.epam.pharmacy.entity.Drug;
import by.epam.pharmacy.entity.Order;
import by.epam.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Lenovo on 16.06.2016.
 */
public class SearchOrderLogic {
    private final static Logger LOG = LogManager.getLogger(LoginLogic.class);

    public Order find(int id) {
        Order order = null;
        OrderDAO orderDAO = new OrderDAO();
        try {
            order = orderDAO.findOrderByOrderId(id);
        } catch (DAOException e) {
            LOG.error(e);
        }
        finally {
            orderDAO.closeConnection();
        }
        return order;
    }
}
