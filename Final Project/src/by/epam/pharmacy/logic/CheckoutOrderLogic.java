package by.epam.pharmacy.logic;

import by.epam.pharmacy.dao.DrugListDao;
import by.epam.pharmacy.dao.OrderDAO;
import by.epam.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Lenovo on 18.06.2016.
 */
public class CheckoutOrderLogic {
    private final static Logger LOG = LogManager.getLogger(LoginLogic.class);

    public boolean checkout(Integer id) {
        boolean result = false;
        OrderDAO orderDAO = new OrderDAO();
        try {

            orderDAO.deleteDrugsOrderedById(id);
            result =  orderDAO.deleteOrderById(id);
        } catch (DAOException e) {
            LOG.error(e);
        }
        finally {
            orderDAO.closeConnection();
        }
        return result;
    }
}
