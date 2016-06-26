package by.epam.pharmacy.logic;

import by.epam.pharmacy.dao.DrugListDao;
import by.epam.pharmacy.entity.Drug;
import by.epam.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Lenovo on 20.06.2016.
 */
public class AddProductLogic {
    private final static Logger LOG = LogManager.getLogger(RegisterUserLogic.class);

    public boolean add(String drugName, int amount) {
        boolean result = false;
        DrugListDao drugListDao = new DrugListDao();
        try {
            drugListDao.addProduct(drugName, amount);
            result = true;
        } catch (DAOException e) {
            LOG.error(e);
        } finally {
            drugListDao.closeConnection();
        }
        return result;
    }
}
