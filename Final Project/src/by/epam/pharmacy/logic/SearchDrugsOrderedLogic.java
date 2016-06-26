package by.epam.pharmacy.logic;

import by.epam.pharmacy.dao.DrugListDao;
import by.epam.pharmacy.dao.OrderDAO;
import by.epam.pharmacy.entity.DrugOrdered;
import by.epam.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by Lenovo on 17.06.2016.
 */
public class SearchDrugsOrderedLogic {
    private final static Logger LOG = LogManager.getLogger(LoginLogic.class);

    public ArrayList search(int id) {
        ArrayList<DrugOrdered> drugOrdereds = new ArrayList<>();
        OrderDAO orderDAO = new OrderDAO();
        try {
            drugOrdereds = orderDAO.findDrugOrderedByOrderId(id);
        } catch (DAOException e) {
            LOG.error(e);
        }
        finally {
            orderDAO.closeConnection();
        }
        return drugOrdereds;
    }
}
