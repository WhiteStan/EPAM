package by.epam.pharmacy.logic;

import by.epam.pharmacy.dao.DrugListDao;
import by.epam.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddProductLogic {
    private final static Logger LOG = LogManager.getLogger(RegisterUserLogic.class);

    public boolean add(String drugName, int amount) {
        boolean result = false;
        DrugListDao drugListDao = new DrugListDao();
        try {
            result = drugListDao.addProduct(drugName, amount);
        } catch (DAOException e) {
            LOG.error(e);
        } finally {
            drugListDao.closeConnection();
        }
        return result;
    }
}
