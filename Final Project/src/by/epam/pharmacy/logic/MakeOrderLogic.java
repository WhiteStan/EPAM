package by.epam.pharmacy.logic;

import by.epam.pharmacy.dao.DrugListDao;
import by.epam.pharmacy.dao.OrderDAO;
import by.epam.pharmacy.entity.DrugOrdered;
import by.epam.pharmacy.entity.Order;
import by.epam.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class MakeOrderLogic {
    private final static Logger LOG = LogManager.getLogger(LoginLogic.class);

    public boolean addOrder(ArrayList<DrugOrdered> drugs, String login) {
        int totalPrice = 0;
        boolean result = false;
        DrugListDao drugListDao = new DrugListDao();
        OrderDAO orderDAO = new OrderDAO();
        try {
            for (DrugOrdered drug : drugs) {
                totalPrice += drugListDao.getPriceByName(drug.getName()) * drug.getAmount();
                drugListDao.reduceInStock(drug.getName(), drug.getAmount());
            }
            Order order = new Order(totalPrice, login);
            orderDAO.addOrder(order);
            int orderId = orderDAO.findLastId();
            for (DrugOrdered drug : drugs) {
                orderDAO.addDrugOrdered(drug, orderId);
            }
            result = true;
        } catch (DAOException e) {
            LOG.error(e);
        }
        return result;
    }
}
